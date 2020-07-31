<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<%--Dùng để gọi hàm trả về khi dùng ajax jquery--%>
<c:url var="APIurl" value="/api-admin-new"/>
<c:url var="NewURL" value="/admin-new"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chỉnh sửa bài viết</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="<c:url value="/admin-new?type=list&page=1&maxPageItem=2&sortName=title&sortBy=desc"/>">Trang chủ</a>
                </li>
                <li class="active">Chỉnh sửa bài viết</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row" >
                <div class="col-xs-12">
                    <c:if test="${ not empty message}">
                        <div class="alert alert-${alert}">
                            ${message}
                        </div>
                    </c:if>
                    <form id="formSubmit">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Thể loại</label>
                            <div class="col-sm-9">
                                <select class="form-control" id="categoryCode" name="categoryCode">
                                    <option value="">Chọn loại bài viết</option>
                                    <c:if test="${empty model.categoryCode}">
                                        <c:forEach items="${categories}" var="item">
                                            <option value="${item.code}">${item.name}</option>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${not empty model.categoryCode}">
                                        <c:forEach items="${categories}" var="item">
                                            <option value="${item.code}"
                                                    <c:if test="${item.code == model.categoryCode}">selected="selected"</c:if>>
                                                    ${item.name}
                                            </option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Tiêu đề</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="title" name="title" value="${model.title}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Hình đại diện</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="thumbnail" name="thumbnail" value="${model.thumbnail}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Mô tả ngắn</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="shortDescription"
                                       name="shortDescription" value="${model.shortDescription}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Nội dung</label>
                            <div class="col-sm-9">
                                <textarea rows="" cols="" id="content" name="content" style="width: 820px;height: 175px">
                                    ${model.content}
                                </textarea>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${ not empty model.id}">
                                    <input type="button" class="btn btn-white btn-primary btn-bold"
                                           value="Cập nhật bài viết" id="btnAddOrUpdateNew" />
                                </c:if>
                                <c:if test="${ empty model.id}">
                                    <input type="button" class="btn btn-white btn-primary btn-bold"
                                           value="Thêm mới bài viết" id="btnAddOrUpdateNew" />
                                </c:if>
                            </div>
                        </div>
                        <input type="hidden" id="id" name="id" value="${model.id}"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div><!-- /.main-content -->
<%--click jquery action cho form submit--%>
<script type="text/javascript">
    <%--  Đoạn code biến textarea thành ckeditor  --%>
    var editor = '';
    $(document).ready(function () {
        editor = CKEDITOR.replace('content');
    });

    $('#btnAddOrUpdateNew').click(function (e) {
        //Phải có lệnh này nếu không sẽ bị nhầm url
        e.preventDefault();
        // var categoryCode = $('#categoryCode').val();
        // var title = $('#title').val();
        // var thumbnail = $('#thumbnail').val();
        // var shortDescription = $('#shortDescription').val();
        // var content = $('#content').val();

        // Dùng mảng để chứa các thông tin trong form theo trường name của input
        var data = {};
        var formData = $('#formSubmit').serializeArray();
        $.each(formData, function (i, v) {
            data["" + v.name + ""] = v.value;
        });

        data['content'] = editor.getData();

        var id = $('#id').val();
        if (id === ""){
            addNew(data);
        }
        else {
            addUpdateNew(data);
        }

        function addNew(data) {
            $.ajax({
                url: '${APIurl}',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),         // Câu lệnh chuyển data kiểu javascriptObject thành Json
                dataType: 'json',
                success: function (result) {
                    window.location.href = "${NewURL}?type=edit&id=" + result.id + "&message=insert_success";
                },
                error: function (error) {
                    window.location.href = "${NewURL}?type=list&page=1&maxPageItem=2&message=error_system"
                }
            });
        }

        function addUpdateNew(data) {
            $.ajax({
                url: '${APIurl}',
                type: 'PUT',
                contentType: 'application/json',    // Định dạng kiểu dữ liệu gửi lên server
                data: JSON.stringify(data),         // Câu lệnh chuyển data kiểu javascriptObject thành Json
                dataType: 'json',                   // Định dạng kiểu dữ liệu server trả về
                success: function (result) {
                    window.location.href = "${NewURL}?type=edit&id=" + result.id + "&message=update_success";
                },
                error: function (error) {
                    window.location.href = "${NewURL}?type=list&page=1&maxPageItem=2&message=error_system"
                }
            });
        }
    })
</script>
</body>
</html>