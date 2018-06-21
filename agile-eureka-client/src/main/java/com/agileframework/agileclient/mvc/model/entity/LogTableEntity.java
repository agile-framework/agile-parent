package com.agileframework.agileclient.mvc.model.entity;

import com.agileframework.agileclient.common.annotation.Remark;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by 佟盟
 */
@Entity
@Table(name = "log_table",  catalog = "agile_db")
@Remark("[系统管理]日志相关表变动信息")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LogTableEntity implements Serializable {

    //序列
    private static final long serialVersionUID = 1L;
    @Remark("唯一标识")
    private Integer logTableId;
    @Remark("日志标识")
    private int logMainId;
    @Remark("数据库")
    private String tableSchema;
    @Remark("表名")
    private String tableName;
    @Remark("操作类型")
    private String operationType;
    @Remark("操作顺序")
    private int operationOrder;

    //无参构造器
    public LogTableEntity(){}

    //有参构造器
    public LogTableEntity(Integer logTableId,int logMainId,String tableSchema,String tableName,String operationType,int operationOrder){
        this.logTableId = logTableId;
        this.logMainId = logMainId;
        this.tableSchema = tableSchema;
        this.tableName = tableName;
        this.operationType = operationType;
        this.operationOrder = operationOrder;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "log_table_id" , nullable = false )
    public Integer getLogTableId() {
        return logTableId;
    }

    public void setLogTableId(int logTableId) {
        this.logTableId = logTableId;
    }

    @Basic
    @Column(name = "log_main_id" , nullable = false )
    public int getLogMainId() {
        return logMainId;
    }

    public void setLogMainId(int logMainId) {
        this.logMainId = logMainId;
    }

    @Basic
    @Column(name = "table_schema" , nullable = false )
    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    @Basic
    @Column(name = "table_name" , nullable = false )
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "operation_type" , nullable = false )
    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    @Basic
    @Column(name = "operation_order" , nullable = false )
    public int getOperationOrder() {
        return operationOrder;
    }

    public void setOperationOrder(int operationOrder) {
        this.operationOrder = operationOrder;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof LogTableEntity)) return false;
        LogTableEntity that = (LogTableEntity) object;
        return Objects.equals(getLogTableId(), that.getLogTableId()) &&
            Objects.equals(getLogMainId(), that.getLogMainId()) &&
            Objects.equals(getTableSchema(), that.getTableSchema()) &&
            Objects.equals(getTableName(), that.getTableName()) &&
            Objects.equals(getOperationType(), that.getOperationType()) &&
            Objects.equals(getOperationOrder(), that.getOperationOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogTableId(), getLogMainId(), getTableSchema(), getTableName(), getOperationType(), getOperationOrder());
    }

    @Override
    public String toString() {
        return "LogTableEntity{" +
        "logTableId=" + logTableId +
        ",logMainId=" + logMainId +
        ",tableSchema='" + tableSchema + '\'' +
        ",tableName='" + tableName + '\'' +
        ",operationType='" + operationType + '\'' +
        ",operationOrder=" + operationOrder +
        '}';
    }

    private LogTableEntity(Builder builder){
        this.logTableId = builder.logTableId;
        this.logMainId = builder.logMainId;
        this.tableSchema = builder.tableSchema;
        this.tableName = builder.tableName;
        this.operationType = builder.operationType;
        this.operationOrder = builder.operationOrder;
    }

    public static class Builder{
        private Integer logTableId;
        private int logMainId;
        private String tableSchema;
        private String tableName;
        private String operationType;
        private int operationOrder;

        public Builder setLogTableId(int logTableId) {
            this.logTableId = logTableId;
            return this;
        }
        public Builder setLogMainId(int logMainId) {
            this.logMainId = logMainId;
            return this;
        }
        public Builder setTableSchema(String tableSchema) {
            this.tableSchema = tableSchema;
            return this;
        }
        public Builder setTableName(String tableName) {
            this.tableName = tableName;
            return this;
        }
        public Builder setOperationType(String operationType) {
            this.operationType = operationType;
            return this;
        }
        public Builder setOperationOrder(int operationOrder) {
            this.operationOrder = operationOrder;
            return this;
        }
        public LogTableEntity build(){
            return new LogTableEntity(this);
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
