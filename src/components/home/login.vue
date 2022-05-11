<template>
  <el-container class="login-container">
    <el-main>
      <el-form
        ref="loginForm"
        :model="loginForm"
        :rules="loginRules"
        class="login-form"
        autocomplete="on"
        label-position="left"
      >
        <div class="login-main">
          <div class="title-container">
            <h3 class="title">东华大学ACM管理平台</h3>
          </div>

          <form class="login" action="">
            <div class="login__field">
              <i class="iconfont icon-yonghuming"></i>
              <input
                type="text"
                class="login__input"
                placeholder="用户名"
                v-model="username"
              />
            </div>
            <div class="login__field">
              <i class="iconfont icon-mima"></i>
              <input
                type="password"
                class="login__input"
                placeholder="密码"
                v-model="password"
              />
            </div>

            <div style="position: relative">
              <el-row :gutter="10">
                <el-col :span="12">
                  <el-button
                    :loading="loading"
                    type="primary"
                    style="width: 100%; margin-bottom: 30px"
                    @click.native.prevent="onSubmit">登录
                  </el-button>
                </el-col>

                <el-col :span="12">
                  <el-button
                    type="warning"
                    style="width: 100%; margin-bottom: 30px"
                    @click.native.prevent="studentRegister">学员注册</el-button>
                </el-col>
              </el-row>
            </div>
          </form>
        </div>
      </el-form>
    </el-main>
  </el-container>

</template>
<script>
export default {
  components: {},
  data() {
    return {
      username: "",
      password: "",
      captchaCodeUrl: this.GLOBAL.BaseUrl + "/login",
      captchaCode: "",
    };
  },
  mounted() {
    console.log("ok");
  },
  methods: {
    studentRegister() {
      this.$router.push('/User/register')
    },
    onSubmit() {
      let formData = new FormData();
      formData.set("username", this.username);
      formData.set("password", this.password);
      this.axios
        .post(this.GLOBAL.BaseUrl + "/Login/login", formData)
        .then((res) => {
          if (res.data.code !== 200) {
            this.error(res.data.message);
          } else {
            //请求用户信息.
            this.axios
              .get(this.GLOBAL.BaseUrl + "/User/getUsers")
              .then((res) => {
                if (res.data.code === 200) {
                  sessionStorage.setItem(
                    "userInfo",
                    JSON.stringify(res.data.data)
                  );
                  this.$router.push({ path: "/home" });
                } else {
                  console.log.warn("获取用户信息失败");
                }
              });
          }
        });
    },

    //消息提醒
    error(msg) {
      this.$Notice.error({
        title: msg,
      });
    },
  },
};
</script>
<style>
@import url("https://fonts.googleapis.com/css?family=Raleway:400,700");

a {
  text-decoration: none;
  color: white;
  font-weight: 700;
  margin-top: 20px;
  border-bottom: 2px solid #d1d1d4;
}

* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  font-family: Raleway, sans-serif;
}
body {
  min-height: 100%;
  width: 100%;
  background: url("../../assets/bg2.png") center center;
}
h3 {
  font-size: 22px;
  color: #174963;
  font-weight: 700;
}
.login-form {
  position: relative;
  width: 450px;
  max-width: 100%;
  padding: 120px 0 0;
  margin: 0 auto;
  overflow: hidden;
}
.login-main {
    padding: 25px 15px 15px 15px;
    background-color: rgba(255,255,255,0.8);
    border-radius: 5px;
}
.login {
  border-radius: 5px;
  width: 420px;
  padding-top: 25px;
}
.login__field {
  padding: 20px 0px;
  position: relative;
}

.login__icon {
  position: absolute;
  top: 35px;
  padding: 0px 10px;
  color: #648cff;
}

.login__input {
  border: none;
  border-bottom: 2px solid #d1d1d4;
  font-size: 16px;
  background: none;
  padding: 10px;
  padding-left: 30px;
  font-weight: 600;
  width: 75%;
  transition: 0.2s;
}

.login__input:active,
.login__input:focus,
.login__input:hover {
  outline: none;
  border-bottom-color: #6a679e;
}
</style>
