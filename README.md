# 知乎
最后的项目   
## 用户登录注册

### 登录

1.输入框限制位数

2.具体提醒输入错误（密码、用户名或账号）

3.圆角按钮

4.提示文字

5.记住密码（使用SharedPreferences）（发现用户更改信息后SharedPreferences不能动态变化）

### 注册

1.圆角按钮

2.提示用户名不能重复

3.提示一个手机号不能重复注册

## 栏目总览（卡片式布局）

1.滑动菜单

2.标题栏下滑隐藏，上滑显示

3.栏目下拉刷新

4.标题栏中点击进入热门消息

## 滑动菜单内容

### 头部

1.用户头像

可点击选择更改头像（使用PopupWindow）

2.用户名（可在个人资料中更改）

### 菜单部分

1.个人资料

可在其中展示资料和修改资料（其中在选择性别时使用RadioButton）

2.历史记录（暂无功能）

3.文章收藏（卡片式布局）

点击进入可查看用户所收藏的文章，点击卡片可进入文章内容

4.栏目收藏（卡片式布局）

点击进入可查看用户所收藏的栏目，点击卡片可进入栏目

5.喜爱文章（卡片式布局）

点击进入可查看用户所喜爱的文章，点击卡片可进入文章内容

6.喜爱专栏（卡片式布局）

点击进入可查看用户所喜爱的栏目，点击卡片可进入栏目内容

7.夜间模式

点击可切换夜间模式或日间模式

8.退出账号（使用Snackbar提醒）

确认后返回登录页面

## 栏目详情（片式布布局）

1.标题栏可上滑隐藏

2.标题栏中带有菜单，可实现收藏（取消收藏）与喜爱（取消喜爱）功能

## 热门消息（卡片式布局）

1.标题栏可上滑隐藏

2.卡片可点击进入文章

## 文章详情（webview布局）

1.这里使用滑动菜单来显示文章热度和评论

2.菜单中也能实现收藏（取消收藏）与喜爱（取消喜爱）功能

3.返回按钮置于右上方

---------------------------------------------------------------------------------------

## 数据库中所含表格

1.用户信息表

2.收藏栏目表

3.收藏文章表

4.喜爱栏目表

5.喜爱文章表
