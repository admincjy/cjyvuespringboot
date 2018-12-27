<template>
  <div>
    <div style="width: 700px;">
    	<el-tag type="warning">当前在线{{}}人</el-tag>
    </div>
    <el-container>
      <el-main style="padding-top: 0px;padding-bottom: 0px">
        <div class="chatDiv" ref="chatDiv" id="chatDiv">
        	<template>
   <!--       <template v-for="msg in msgList">-->
            <!--发送来的消息-->
            <!--<div
              style="display: flex;justify-content: flex-start;align-items: center;box-sizing: border-box;"
              v-if="msg.from==currentFriend.username">
              <img :src="currentFriend.userface" class="userfaceImg">
              <div
                style="display: inline-flex;border-style: solid;border-width: 1px;border-color: #20a0ff;border-radius: 5px;padding: 5px 8px 5px 8px">
                {{msg.msg}}
              </div>
            </div>-->
            <!--发出去的消息-->
            <div style="display: flex;justify-content: flex-end;align-items: center;box-sizing: border-box;">
              <div
                style="display: inline-flex;border-style: solid;border-width: 1px;border-color: #20a0ff;border-radius: 5px;padding: 5px 8px 5px 8px;margin-right: 3px;background-color: #9eea6a">
                {{this.sendmsg}}
              </div>
       <!--       <img :src="currentUser.userface" class="userfaceImg">-->
            </div>
          </template>
        </div>
        <div style="text-align: left;margin-top: 10px">
          <el-input v-model="sendmsg" placeholder="请输入内容" size="mini" style="width: 600px;" type="textarea"
                    autosize></el-input>
          <el-button size="small" type="primary" class="sendBtn" @click="sendMsg"><i
            class="fa fa-send"
            style="margin-right: 15px"></i>发送
          </el-button>
        </div>
      </el-main>
    </el-container>
  </div>
</template>
<script>
  export default{
    data(){
      return {
      	username:window.localStorage.name,
        hrs: [],
        msg: '',
        sendmsg:'',
        currentUser: this.$store.state.user,
        currentFriend: {}
      }
    },
    mounted: function () {
      this.loadHrs();
      this.getWebSocket();
    },
    methods: {
    	getWebSocket(){
    		 /**ws://10.2.15.92:8080/chat
         * WebSocket客户端 PS：URL开头表示WebSocket协议 中间是域名端口 结尾是服务端映射地址
         */
        var websocket = new WebSocket('/ws/chat');
        console.log(websocket)
        /**
         * 当服务端打开连接
         */
        webSocket.onopen = function (event) {
            console.log('WebSocket打开连接');
        };

        /**
         * 当服务端发来消息：1.广播消息 2.更新在线人数
         */
        webSocket.onmessage = function (event) {
            console.log('WebSocket收到消息：%c' + event.data, 'color:green');
            //获取服务端消息
            var message = JSON.parse(event.data) || {};
            var $messageContainer = $('.message-container');
            //喉咙发炎
            if (message.type === 'SPEAK') {
                $messageContainer.append(
                    '<div class="mdui-card" style="margin: 10px 0;">' +
                    '<div class="mdui-card-primary">' +
                    '<div class="mdui-card-content message-content">' + message.username + "：" + message.msg + '</div>' +
                    '</div></div>');
            }
            $('.chat-num').text(message.onlineCount);
            //防止刷屏
            var $cards = $messageContainer.children('.mdui-card:visible').toArray();
            if ($cards.length > 5) {
                $cards.forEach(function (item, index) {
                    index < $cards.length - 5 && $(item).slideUp('fast');
                });
            }
	      };
        /**
         * 关闭连接
         */
        webSocket.onclose = function (event) {
            console.log('WebSocket关闭连接');
        };
	
        /**
         * 通信失败
         */
        webSocket.onerror = function (event) {
            console.log('WebSocket发生异常');

        };
        return webSocket;
    	},
    	sendMsg(){
//  		console.log(window.localStorage.getItem("user"))
//    	 this.getWebSocket.send(JSON.stringify({username: this.username, msg:this.sendmsg}))
      },
      updateChatDiv(){
        var oldMsg = window.localStorage.getItem(this.currentUser.username + "#" + this.currentFriend.username);
        if (oldMsg == null) {
          this.$store.commit('updateMsgList', [])
        } else {
          this.$store.commit('updateMsgList', JSON.parse(oldMsg))
        }
      },
      toggleFriend(hr){
        //切换数据
        if (hr == this.currentFriend) {
          return;
        }
        this.currentFriend = hr;
        this.$store.commit('updateCurrentFriend', hr);
        this.updateChatDiv();
        this.$store.commit("removeValueDotMap", "isDot#" + this.currentUser.username + "#" + hr.username);
        document.getElementById('chatDiv').scrollTop = document.getElementById('chatDiv').scrollHeight;
      },
      loadHrs(){
        var _this = this;
        this.getRequest("/Hr/all").then(resp=> {
          _this.hrs = resp.data.aaData;
          for(var i=5;i<resp.data.aaData.length;i++){
            	_this.hrs[i].userface='/servlet/getfile?file=' + resp.data.aaData[i].userface;
           }
        })
      }
    }
  }
</script>
<style>
  .userfaceImg {
    width: 37px;
    height: 37px;
    border-radius: 30px;
    margin-right: 10px;
  }

  .friendListDiv {
    display: flex;
    justify-content: flex-start;
    padding-left: 20px;
    box-sizing: border-box;
    align-items: center;
    width: 160px;
    height: 40px;
    border-color: #20a0ff;
    border-left-style: solid;
    border-top-style: solid;
    border-right-style: solid;
    border-width: 1px;
    cursor: pointer
  }

  .chatDiv {
    border-color: #20a0ff;
    border-style: solid;
    border-radius: 5px;
    border-width: 1px;
    box-sizing: border-box;
    width: 700px;
    height: 450px;
    overflow-y: auto;
    padding-bottom: 50px;
  }

  .sendBtn {
    padding-left: 25px;
    padding-right: 25px
  }

  .currentChatFriend {
    background-color: #dcdfe6;
  }
</style>
