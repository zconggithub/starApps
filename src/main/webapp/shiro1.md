【什么是Realm】
在我所看的学习资料中，关于Realm的定义，写了整整一长串，但是对于初学者来说，看定义实在是太头疼了。
对于什么是Realm，我使用过之后，个人总结一下：
shiro要进行身份验证，就要从realm中获取相应的身份信息来进行验证，简单来说，
我们可以自行定义realm，在realm中，从数据库获取身份信息，然后和 用户输入的身份信息进行匹配。这一切都由我们自己来定义。
                    继承AuthorizingRealm