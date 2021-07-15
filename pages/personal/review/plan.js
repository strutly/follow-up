var that;
const util = require("../../../utils/util");
const config = require("../../../config/config");
const app = getApp();
Page({
  data: {
    code:0,
    compare:app.globalData.compare,
    reviewPlans:[]
  },
  async onLoad(options) {
    that = this;
    let res = await util.sendAjax(config.PersonalPlanList,{},"get");
    console.log(res);
    that.setData({
      code:res.code,
      msg:res.msg,
      reviewPlans:res.data
    })
  },
  detail(e){
    let id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: `/pages/personal/review/detail?id=${id}`,
    })
  },
  prompt(){
    util.prompt(that,"您暂未提交检测信息");
  }
})