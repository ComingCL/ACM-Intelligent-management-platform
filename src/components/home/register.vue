<template>
  <div class="login-container">
    <div class="login-form">
      <div class="login-main">
        <div class="title-container">
          <h3 class="title">东华大学ACM管理平台</h3>
        </div>

        <form class="login" action="">
          <div class="login__field">
            <i class="iconfont icon-yonghuming"></i>
            <input
              v-model="username"
              type="text"
              class="login__input"
              placeholder="登录名"
            />
          </div>
          <div class="login__field">
            <i class="iconfont icon-qiyedengluyonghuming"></i>
            <input
              v-model="nickName"
              type="text"
              class="login__input"
              placeholder="昵称"
            />
          </div>
          <div class="login__field">
            <i class="iconfont icon-mima"></i>
            <input
              v-model="password"
              type="password"
              class="login__input"
              placeholder="密码"
            />
          </div>
          <div class="login__field">
            <i class="iconfont icon-youxiang"></i>
            <input
              v-model="email"
              type="text"
              class="login__input"
              placeholder="邮箱"
            />
          </div>
          <div class="login__field">
            <i class="iconfont icon-shoujihao"></i>
            <input
              v-model="phone"
              type="text"
              class="login__input"
              placeholder="手机号"
            />
          </div>
        </form>

        <div style="position: relative">
          <el-row :gutter="24">
            <el-col :span="16">
              <el-button
                type="warning"
                style="width: 100%; margin-bottom: 30px"
                icon="el-icon-user-solid"
                @click.native.prevent="onSubmit"
                >注册</el-button
              >
            </el-col>

            <el-col :span="8">
              <el-button
                style="width: 100%; margin-bottom: 30px"
                icon="el-icon-s-promotion"
                @click.native.prevent="toLogin"
                >去登录</el-button
              >
            </el-col>
          </el-row>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  computed: {},
  data() {
    return {
      userName: "",
      nickName: "",
      password: "",
      phone: "",
      email: "",
    };
  },

  methods: {
    toLogin() {
      this.$router.push("/user/login");
    },
    onSubmit() {
      let formData = new FormData();
      formData.set("username", this.username);
      formData.set("password", this.password);
      formData.set("nickName", this.nickName);
      formData.set("email", this.email);
      formData.set("phone", this.phone);
      this.axios
        .post(this.GLOBAL.BaseUrl + "/register", formData)
        .then((res) => {
          console.log(res);
          if (res.data.code !== 201) {
            this.$Notice.warning({ title: res.data.message });
          } else {
            this.$Notice.success({ title: res.data.message });
            setTimeout(() => {
              this.$router.replace({ path: "/user/login" });
            }, 1000);
          }
        });
    },
    showPwd() {
      if (this.passwordType === "password") {
        this.passwordType = "";
      } else {
        this.passwordType = "password";
      }
      this.$nextTick(() => {
        this.$refs.password.focus();
      });
    },
  },
};
</script>

<style>
body {
  min-height: 100%;
  width: 100%;
  background: url("../../assets/bg2.png");
}
h3 {
  font-size: 22px;
  color: #174963;
  font-weight: 700;
}
.login-form {
  position: relative;
  width: 550px;
  max-width: 100%;
  padding: 160px 35px 0;
  margin: 0 auto;
  overflow: hidden;
}
.login-main {
  padding: 25px 15px 15px 15px;
  background-color: rgba(255, 255, 255, 0.8);
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
