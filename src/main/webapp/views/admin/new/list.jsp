<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<%--Dùng để gọi hàm trả về khi dùng ajax jquery--%>
<c:url var="APIurl" value="/api-admin-new"/>
<c:url var="NewURL" value="/admin-new"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<title>Danh sách bài viết</title>
</head>

<body>
	<form action='<c:url value="/admin-new"/>' id="formSubmit" method="get">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li>
						<i class="ace-icon fa fa-home home-icon"></i>
						<a href="#">Danh sách bài viết</a>
					</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
                        <c:if test="${ not empty message}">
                            <div class="alert alert-${alert}">
                                    ${message}
                            </div>
                        </c:if>
						<div class="widget-box table-filter">
							<div class="table-btn-controls">
								<div class="pull-right tableTools-container">
									<div class="dt-buttons btn-overlap btn-group">
										<a flag="info"
										   class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
										   title='Thêm bài viết' href='<c:url value="/admin-new?type=edit"/>'>
												<span>
													<%--Lấy các icon từ: https://fontawesome.com/v4.7.0/icons/--%>
													<i class="fa fa-plus-circle bigger-110 purple"></i>
												</span>
										</a>
										<button id="btnDelete" type="button"
												class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Xóa bài viết'>
												<span>
													<i class="fa fa-trash-o bigger-110 pink"></i>
												</span>
										</button>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<div class="table-responsive">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th><input type="checkbox" id="checkAll"></th>
												<th>Tên bài viết</th>
												<th>Mô tả ngắn</th>
												<th>Thao tác</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${model.listResult}" var="item">
												<tr>
													<th><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></th>
													<td>${ item.title }</td>
													<td>${ item.shortDescription }</td>
													<td>
														<c:url var="editURL" value="/admin-new">
															<c:param name="type" value="edit"/>
															<c:param name="id" value="${item.id}"/>
														</c:url>
														<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
														   title="Cập nhật bài viết" href='${editURL}'>
															<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
														</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<!-- Phân trang -->
									<ul class="pagination" id="pagination"></ul>
									<input type="hidden" value="" id="page" name="page"/>
									<input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
									<input type="hidden" value="" id="sortName" name="sortName"/>
									<input type="hidden" value="" id="sortBy" name="sortBy"/>
									<input type="hidden" value="" id="type" name="type"/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		var totalPages = ${ model.totalPage };
		var currentPage = ${ model.page };		
		var limit = 2;
		$(function () {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages: totalPages,
				visiblePages: 10,
				startPage: currentPage,
				onPageClick: function (event, page) {
					//console.info(page + ' (from options)');
					//jQuery Event
					if (currentPage !== page) {
						$('#maxPageItem').val(limit);
						$('#page').val(page);
						$('#sortName').val('title');
						$('#sortBy').val('desc');
						$('#type').val('list');
						$('#formSubmit').submit();
					}
				}
			});
		});
		$('#btnDelete').click(function () {
			var data = {};
			// Mảng tìm các input có type là checkbox và có đánh dấu là checked thì lưu lại
			var ids = $('tbody input[type=checkbox]:checked').map(function () {
				return $(this).val();
			}).get();
			data['ids'] = ids;
			deleteNew(data);
		});
		function deleteNew(data) {
			$.ajax({
				url: '${APIurl}',
				type: 'DELETE',
				contentType: 'application/json',
				data: JSON.stringify(data),         // Câu lệnh chuyển data kiểu javascriptObject thành Json
				// dataType: 'json',         Backend trả về rổng nên k cần đoạn này
				success: function (result) {
					// JavaScript Window Location
					// Reload lại trang
					window.location.href = "${NewURL}?type=list&page=1&maxPageItem=2&message=delete_success"
				},
				error: function (error) {
                    window.location.href = "${NewURL}?type=list&page=1&maxPageItem=2&message=error_system"
				}
			});
		}
	</script>
</body>

</html>