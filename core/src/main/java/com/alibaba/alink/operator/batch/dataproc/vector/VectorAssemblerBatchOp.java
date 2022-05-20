package com.alibaba.alink.operator.batch.dataproc.vector;

import org.apache.flink.ml.api.misc.param.Params;

import com.alibaba.alink.operator.common.dataproc.vector.VectorAssemblerMapper;
import com.alibaba.alink.operator.batch.utils.MapBatchOp;
import com.alibaba.alink.params.dataproc.vector.VectorAssemblerParams;

/**
 * VectorAssembler is a transformer that combines a given list of columns(vector or numerical column) into a single
 * vector column. It is useful for combining features generated by different feature generators into a
 * single feature vector. VectorAssembler accepts the following input column types: all numeric types, and vector type.
 * In each row, the values of the input columns will be concatenated into a vector in the specified order.
 *
 * this operator can transform batch data.
 */
public final class VectorAssemblerBatchOp extends MapBatchOp <VectorAssemblerBatchOp>
	implements VectorAssemblerParams <VectorAssemblerBatchOp> {

	public VectorAssemblerBatchOp() {
		this(null);
	}

	public VectorAssemblerBatchOp(Params params) {
		super(VectorAssemblerMapper::new, params);
	}
}
