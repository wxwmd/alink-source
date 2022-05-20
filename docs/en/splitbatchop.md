## Description
Split a dataset into two parts.

## Parameters
| Name | Description | Type | Required？ | Default Value |
| --- | --- | --- | --- | --- |
| fraction | Proportion of data allocated to left output after splitting | Double | ✓ |  |


## Script Example
```python
URL = "http://alink-dataset.cn-hangzhou.oss.aliyun-inc.com/csv/iris.csv"
SCHEMA_STR = "sepal_length double, sepal_width double, petal_length double, petal_width double, category string";
data = CsvSourceBatchOp().setFilePath(URL).setSchemaStr(SCHEMA_STR)
spliter = SplitBatchOp().setFraction(0.4)
train_data = spliter
test_data = spliter.getSideOutput(0)
```

