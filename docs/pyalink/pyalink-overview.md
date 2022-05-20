PyAlink 使用介绍
===============


使用前准备：
---------

1. 确保使用环境中有Python3，版本>=3.5。
2. 根据 Python 版本下载对应的 pyalink 包： 
      - Python 3.5：[链接1](https://alink-release.oss-cn-beijing.aliyuncs.com/v1.0.1/pyalink-1.0.1_flink_1.9.0_scala_2.11-py3.5.egg) [链接2](https://github.com/alibaba/Alink/releases/download/v1.0.1/pyalink-1.0.1_flink_1.9.0_scala_2.11-py3.5.egg) (MD5: 9714e5e02b4681a55263970abc6dbe57)
      - Python 3.6：[链接1](https://alink-release.oss-cn-beijing.aliyuncs.com/v1.0.1/pyalink-1.0.1_flink_1.9.0_scala_2.11-py3.6.egg) [链接2](https://github.com/alibaba/Alink/releases/download/v1.0.1/pyalink-1.0.1_flink_1.9.0_scala_2.11-py3.6.egg) (MD5: 112638a81c05f1372f9dac880ec527e6)
      - Python 3.7：[链接1](https://alink-release.oss-cn-beijing.aliyuncs.com/v1.0.1/pyalink-1.0.1_flink_1.9.0_scala_2.11-py3.7.egg) [链接2](https://github.com/alibaba/Alink/releases/download/v1.0.1/pyalink-1.0.1_flink_1.9.0_scala_2.11-py3.7.egg) (MD5: 9b483da5176977e4f330ca7675120fed)
      - Python 3.8：[链接1](https://alink-release.oss-cn-beijing.aliyuncs.com/v1.0.1/pyalink-1.0.1_flink_1.9.0_scala_2.11-py3.8.egg) [链接2](https://github.com/alibaba/Alink/releases/download/v1.0.1/pyalink-1.0.1_flink_1.9.0_scala_2.11-py3.8.egg) (MD5: d04aa5d367bc653d5e872e1eba3494cd)
3. 使用 ```easy_install``` 进行安装 ```easy_install [存放的路径]/pyalink-0.0.1-py3.*.egg```。需要注意的是：
    * 如果之前安装过 pyalink，请先使用 ```pip uninstall pyalink``` 卸载之前的版本。
    * 如果有多个版本的 Python，可能需要使用特定版本的 ```easy_install```，比如 ```easy_install-3.7```。
    * 如果使用 Anaconda，则需要在 Anaconda 命令行中进行安装。

开始使用：
-------
我们推荐通过 Jupyter Notebook 来使用 PyAlink，能获得更好的使用体验。

使用步骤：
1. 在命令行中启动Jupyter：```jupyter notebook```，并新建 Python 3 的 Notebook 。
2. 导入 pyalink 包：```from pyalink.alink import *```。
3. 使用方法创建本地运行环境：
```useLocalEnv(parallism, flinkHome=None, config=None)```。
其中，参数 ```parallism``` 表示执行所使用的并行度；```flinkHome``` 为 flink 的完整路径，默认使用 PyAlink 自带的 flink-1.9.0 路径；```config```为Flink所接受的配置参数。运行后出现如下所示的输出，表示初始化运行环境成功：
```
JVM listening on ***
Python listening on ***
```
4. 开始编写 PyAlink 代码，例如：
```
source = CsvSourceBatchOp()\
    .setSchemaStr("sepal_length double, sepal_width double, petal_length double, petal_width double, category string")\
    .setFilePath("http://alink-dataset.cn-hangzhou.oss.aliyun-inc.com/csv/iris.csv")
res = source.select("sepal_length", "sepal_width")
df = res.collectToDataframe()
print(df)
```

编写代码：
------
在 PyAlink 中，算法组件提供的接口基本与 Java API 一致，即通过默认构造方法创建一个算法组件，然后通过 ```setXXX``` 设置参数，通过 ```link/linkTo/linkFrom``` 与其他组件相连。
这里利用 Jupyter 的自动补全机制可以提供书写便利。

对于批式作业，可以通过批式组件的 ```print/collectToDataframe/collectToDataframes``` 等方法或者 ```BatchOperator.execute()``` 来触发执行；对于流式作业，则通过 ```StreamOperator.execute()``` 来启动作业。



更多用法：
------
  - [DataFrame与Operator互转](pyalink-dataframe.md)
  - [StreamOperator数据预览](pyalink-stream-operator-preview.md)
  - [UDF使用](pyalink-udf.md)


Q&A：
----
Q：能否连接远程 Flink 集群进行计算？

A：通过方法可以连接一个已经启动的 Flink 集群：```useRemoteEnv(host, port, parallelism, flinkHome=None, localIp="localhost", shipAlinkAlgoJar=True, config=None)```。其中，参数
  - ```host``` 和 ```port``` 表示集群的地址；
  - ```parallelism``` 表示执行作业的并行度；
  - ```flinkHome``` 为 flink 的完整路径，默认使用 PyAlink 自带的 flink-1.9.0 路径；
  - ```localIp``` 指定实现 ```Flink DataStream``` 的打印预览功能时所需的本机IP地址，需要 Flink 集群能访问。默认为```localhost```。
  - ```shipAlinkAlgoJar``` 是否将 PyAlink 提供的 Alink 算法包传输给远程集群，如果远程集群已经放置了 Alink 算法包，那么这里可以设为 False，减少数据传输。

-----

Q：如何停止长时间运行的Flink作业？

A：使用本地执行环境时，使用 Notebook 提供的“停止”按钮即可。
使用远程集群时，需要使用集群提供的停止作业功能。

-----

Q：能否直接使用 Python 脚本而不是 Notebook 运行？

A：可以。但需要在代码最后调用 resetEnv()，否则脚本不会退出。

-----

