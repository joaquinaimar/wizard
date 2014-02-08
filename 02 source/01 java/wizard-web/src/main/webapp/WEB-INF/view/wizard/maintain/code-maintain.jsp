<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<div class="container">
	<div class="row form-row">
		<div class="col-md-3 input-group">
			<span class="input-group-addon">内容</span> <input type="text"
				id="txtSearchContent" class="form-control" />
		</div>
		<div class="row form-row">
			<div class="col-md-3 btn-group">
				<input type="button" id="btnSearch" class="btn btn-primary"
					value="查询" /> <input type="button" id="btnInsertType"
					class="btn btn-primary" value="新建" /> <input type="button"
					id="btnDeleteType" class="btn btn-primary" value="删除" />
			</div>
			<div class="col-md-3 btn-group">
				<input type="button" id="btnBatchUpdate" class="btn btn-primary"
					value="批量更新" /> <input type="button" id="btnExport"
					class="btn btn-primary" value="导出" />
			</div>
		</div>
		<div class="row">
			<table id="code-type"
				class="table table-striped table-bordered table-hover">
			</table>
		</div>
	</div>
</div>
<div class="modal fade" id="codeListModal" tabindex="-1" role="dialog"
	aria-labelledby="codeList" aria-hidden="true">
	<div class="modal-dialog" style="width: 1000px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="codeList">编码类别</h4>
			</div>
			<div class="modal-body">
				<div class="row form-row">
					<form id="frmCodeType" method="post">
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon">ID</span> <input type="text"
									id="txtpkId" name="pkId" class="form-control" />
							</div>
						</div>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon">内容</span> <input type="text"
									id="txtContent" name="content" class="form-control" />
							</div>
						</div>
						<div class="col-md-3">
							<input type="hidden" id="txtTypeId" name="typeId" value="0" />
						</div>
					</form>
					<div id="code-info-button" class="col-md-3 btn-group">
						<input type="button" id="btnInsertCode" class="btn btn-primary"
							value="新建" /> <input type="button" id="btnDeleteCode"
							class="btn btn-primary" value="删除" />
					</div>
				</div>
				<div id="code-info-zone" class="row form-row">
					<table id="code-info"
						class="table table-striped table-bordered table-hover">
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="btnSaveType" class="btn btn-success">保存</button>
				<button type="button" class="btn btn-warning" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="codeInfoModal" tabindex="-1" role="dialog"
	aria-labelledby="codeList" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="codeList">编码信息</h4>
			</div>
			<div class="modal-body">
				<div class="row form-row">
					<form id="frmCodeInfo" method="post">
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon">类型ID</span> <input type="text"
									id="txtTypeId" name="typeId" class="form-control" readonly />
							</div>
						</div>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon">ID</span> <input type="text"
									id="txtPkId" name="pkId" class="form-control" />
							</div>
						</div>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon">内容</span> <input type="text"
									id="txtContent" name="content" class="form-control" />
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="btnSaveCode" class="btn btn-success">保存</button>
				<button type="button" class="btn btn-warning" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>