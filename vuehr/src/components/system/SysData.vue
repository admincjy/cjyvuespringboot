<template>
  <div>
    <h1>
      备份恢复数据库
    </h1>
       <el-button type="primary" @click="backup" round>一键备份数据库</el-button>
       <el-button type="primary" @click="clean" round>一键清空数据库</el-button>
       <el-button type="primary" @click="backData" round>一键还原至最新数据库</el-button>
  </div>
</template>
<script>
	export default {
		data() {
			return {
			}
		},
		mounted: function () {
    },
    methods:{
    	backup(){
    	  var params={};
    		this.postRequestEx("/backup/databasebackup", params).then(resp => {
				})
			},
			clean(){
    	  var params={};
    		this.postRequestEx("/backup/cleanData", params).then(resp => {
				})
			},
			backData(){
    	  var params={};
    		this.postRequestEx("/backup/backData", params).then(resp => {
				})
			}
    }
	}
</script>
