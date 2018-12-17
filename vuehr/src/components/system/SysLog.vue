<template>
	<div>
		<el-table :data="tableData" style="width: 100%" border>
			<el-table-column prop="title" label="日志标题"></el-table-column>
			<el-table-column prop="type" label="操作类型"></el-table-column>
			<el-table-column prop="hrid" label="操作员"></el-table-column>
			<el-table-column prop="operate" label="操作内容"></el-table-column>
			<el-table-column prop="remoteAddr" label="请求地址"></el-table-column>
			<el-table-column prop="requestUri" label="路径"></el-table-column>
			<el-table-column prop="params" label="参数"></el-table-column>
			<el-table-column prop="createDate" label="开始日期"></el-table-column>
			<el-table-column prop="endTime" label="结束日期"></el-table-column>
			<el-table-column prop="exception" label="是否异常"></el-table-column>
		</el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pageSize"
      :page-sizes="[10, 20, 30, 40]"
      :page-size="100"
      layout="total, sizes, prev, pager, next, jumper"
      :total="400">
    </el-pagination>
  </div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				tableData: [],
				pageNum:1,
				pageSize:10
			}
		},
		mounted: function () {
      this.load();
    },
    methods:{
    	load(){
				var empLoad = {
					page: this.pageNum,
					size: this.pageSize,
					queryData: {}
				}
				this.postRequestEx("/Oplog/list", empLoad).then(resp => {
					this.tableData=resp.data.aaData;
				})
			},
			handleSizeChange(val) {
        this.pageSize=val;
      },
      handleCurrentChange(val) {
      	this.pageNum=val;
      }
    }
	}
</script>