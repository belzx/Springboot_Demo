1：从cache中判断用户是否多次输入密码错误

2：验证密码是否正确。不存在则抛出nofoud异常
userService.selectByUserName(username); 查询
 s_user  s_user_fole  s_roles_role_modules
返回值User 
账号  
密码 
 List<UserRole> userRoles :UserRole{UserRole:userId,roleId,Role:{}}
 Map<Module, Set<String>> roleInfo

3：根据username获取角色信息，及模块信息
如果名称为admin  则获取所有模块的权限

4:将user保存至httpSessionManager中.addUser(user, request.getSession())：保存前user copy一个新的

5：@Authorize(module = "module")实现原理
注解扫描所有的注解


6：AuthenticationHolder：  权限获取器,用于静态方式获取当前登录用户的权限信息.

7：利用aop 调用controller时 通过sessionId 从 httpSessionManager获取user 。再验证模块 以及 角色权力


      




