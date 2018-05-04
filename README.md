# CodeWing [version 0.0.1]

```text
               _
  ___ ___   __| | ___     __      _(_)_ __   __ _
 / __/ _ \ / _` |/ _ \____\ \ /\ / / | '_ \ / _` |
| (_| (_) | (_| |  __/_____\ V  V /| | | | | (_| |
 \___\___/ \__,_|\___|      \_/\_/ |_|_| |_|\__, |
                                            |___/   version 0.0.1
```

CodeWing是一款极其轻量的,能够自动生成代码的自动化工具.核心组件是coder.这个组件可以根据schema文件(包含一些占位符)和bean对象批量生成代码.

由coder可以开发出定制的代码生成器.

目前code-wing只支持Linux,MacOS.要在Windows下使用,需要安装bash来操作.

要想使用CodeWing,您需要从GitHub或码云将项目clone到本地,注意,CodeWing是用Java编写的,在您的机器上需要有Java 1.6以上的运行环境(在您的机器中至少应该定义JAVA_HOME环境变量).

```text
$ git clone -b master https://gitee.com/lazycat_develop/CodeWing.git codewing
```

在bin目录下,您可以看到目前支持的全部CodeWing的Coder(一个Coder负责生成一类代码).

## coder-dao

coder-dao专门用于生成Java Web开发中的DAO相关代码.它根据数据库中的表生成Java Bean,MyBatis的Mapping文件,DAO定义.适用于传统的Spring + MyBatis框架.

要想使用coder-dao,您只需要执行bin目录下的coder-dao程序.这个程序有一些常见的参数需要您设置:

- j: jdbc路径.
- u: 数据库用户.
- p: 数据库密码.
- t: 生成的DAO代码是依靠于数据库中的某张表的,这个参数指明表的名称.
- r: 数据库驱动包名称,可选,默认使用MySQL.
- k: 可选,您的DAO包路径,生成的Java Bean;mapping文件均需要这个定义.不指定的话将使用"codewing.dao".
- e, d, m: 可选,分别指定Java Bean, DAO, mapping文件的输出路径,默认会输出在codewing目录的out下.
- -n: 生成的文件的Java名称,默认将会和您的表名称一样(首字母大写).

注意您需要把数据库驱动文件放到lib下,否则coder-dao将无法连接上对应的数据库.

例如,通过以下方式根据MySQL中test数据库的person表生成DAO代码:

```text
$ coder-dao -j jdbc:mysql://localhost:3306/test -u root -p 12345 -t person
```

随后可以在out目录下看到对应的结果.

更多的参数和说明,您可以直接输入coder-dao查看到.

如果您有问题,发送邮件到lazycat_develop@163.com吧!