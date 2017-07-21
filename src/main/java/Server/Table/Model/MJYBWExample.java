package Server.Table.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MJYBWExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MJYBWExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria and法人代码IsNull() {
            addCriterion("法人代码 is null");
            return (Criteria) this;
        }

        public Criteria and法人代码IsNotNull() {
            addCriterion("法人代码 is not null");
            return (Criteria) this;
        }

        public Criteria and法人代码EqualTo(String value) {
            addCriterion("法人代码 =", value, "法人代码");
            return (Criteria) this;
        }

        public Criteria and法人代码NotEqualTo(String value) {
            addCriterion("法人代码 <>", value, "法人代码");
            return (Criteria) this;
        }

        public Criteria and法人代码GreaterThan(String value) {
            addCriterion("法人代码 >", value, "法人代码");
            return (Criteria) this;
        }

        public Criteria and法人代码GreaterThanOrEqualTo(String value) {
            addCriterion("法人代码 >=", value, "法人代码");
            return (Criteria) this;
        }

        public Criteria and法人代码LessThan(String value) {
            addCriterion("法人代码 <", value, "法人代码");
            return (Criteria) this;
        }

        public Criteria and法人代码LessThanOrEqualTo(String value) {
            addCriterion("法人代码 <=", value, "法人代码");
            return (Criteria) this;
        }

        public Criteria and法人代码Like(String value) {
            addCriterion("法人代码 like", value, "法人代码");
            return (Criteria) this;
        }

        public Criteria and法人代码NotLike(String value) {
            addCriterion("法人代码 not like", value, "法人代码");
            return (Criteria) this;
        }

        public Criteria and法人代码In(List<String> values) {
            addCriterion("法人代码 in", values, "法人代码");
            return (Criteria) this;
        }

        public Criteria and法人代码NotIn(List<String> values) {
            addCriterion("法人代码 not in", values, "法人代码");
            return (Criteria) this;
        }

        public Criteria and法人代码Between(String value1, String value2) {
            addCriterion("法人代码 between", value1, value2, "法人代码");
            return (Criteria) this;
        }

        public Criteria and法人代码NotBetween(String value1, String value2) {
            addCriterion("法人代码 not between", value1, value2, "法人代码");
            return (Criteria) this;
        }

        public Criteria and前台日期IsNull() {
            addCriterion("前台日期 is null");
            return (Criteria) this;
        }

        public Criteria and前台日期IsNotNull() {
            addCriterion("前台日期 is not null");
            return (Criteria) this;
        }

        public Criteria and前台日期EqualTo(Date value) {
            addCriterionForJDBCDate("前台日期 =", value, "前台日期");
            return (Criteria) this;
        }

        public Criteria and前台日期NotEqualTo(Date value) {
            addCriterionForJDBCDate("前台日期 <>", value, "前台日期");
            return (Criteria) this;
        }

        public Criteria and前台日期GreaterThan(Date value) {
            addCriterionForJDBCDate("前台日期 >", value, "前台日期");
            return (Criteria) this;
        }

        public Criteria and前台日期GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("前台日期 >=", value, "前台日期");
            return (Criteria) this;
        }

        public Criteria and前台日期LessThan(Date value) {
            addCriterionForJDBCDate("前台日期 <", value, "前台日期");
            return (Criteria) this;
        }

        public Criteria and前台日期LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("前台日期 <=", value, "前台日期");
            return (Criteria) this;
        }

        public Criteria and前台日期In(List<Date> values) {
            addCriterionForJDBCDate("前台日期 in", values, "前台日期");
            return (Criteria) this;
        }

        public Criteria and前台日期NotIn(List<Date> values) {
            addCriterionForJDBCDate("前台日期 not in", values, "前台日期");
            return (Criteria) this;
        }

        public Criteria and前台日期Between(Date value1, Date value2) {
            addCriterionForJDBCDate("前台日期 between", value1, value2, "前台日期");
            return (Criteria) this;
        }

        public Criteria and前台日期NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("前台日期 not between", value1, value2, "前台日期");
            return (Criteria) this;
        }

        public Criteria and前台流水IsNull() {
            addCriterion("前台流水 is null");
            return (Criteria) this;
        }

        public Criteria and前台流水IsNotNull() {
            addCriterion("前台流水 is not null");
            return (Criteria) this;
        }

        public Criteria and前台流水EqualTo(String value) {
            addCriterion("前台流水 =", value, "前台流水");
            return (Criteria) this;
        }

        public Criteria and前台流水NotEqualTo(String value) {
            addCriterion("前台流水 <>", value, "前台流水");
            return (Criteria) this;
        }

        public Criteria and前台流水GreaterThan(String value) {
            addCriterion("前台流水 >", value, "前台流水");
            return (Criteria) this;
        }

        public Criteria and前台流水GreaterThanOrEqualTo(String value) {
            addCriterion("前台流水 >=", value, "前台流水");
            return (Criteria) this;
        }

        public Criteria and前台流水LessThan(String value) {
            addCriterion("前台流水 <", value, "前台流水");
            return (Criteria) this;
        }

        public Criteria and前台流水LessThanOrEqualTo(String value) {
            addCriterion("前台流水 <=", value, "前台流水");
            return (Criteria) this;
        }

        public Criteria and前台流水Like(String value) {
            addCriterion("前台流水 like", value, "前台流水");
            return (Criteria) this;
        }

        public Criteria and前台流水NotLike(String value) {
            addCriterion("前台流水 not like", value, "前台流水");
            return (Criteria) this;
        }

        public Criteria and前台流水In(List<String> values) {
            addCriterion("前台流水 in", values, "前台流水");
            return (Criteria) this;
        }

        public Criteria and前台流水NotIn(List<String> values) {
            addCriterion("前台流水 not in", values, "前台流水");
            return (Criteria) this;
        }

        public Criteria and前台流水Between(String value1, String value2) {
            addCriterion("前台流水 between", value1, value2, "前台流水");
            return (Criteria) this;
        }

        public Criteria and前台流水NotBetween(String value1, String value2) {
            addCriterion("前台流水 not between", value1, value2, "前台流水");
            return (Criteria) this;
        }

        public Criteria and前台交易码IsNull() {
            addCriterion("前台交易码 is null");
            return (Criteria) this;
        }

        public Criteria and前台交易码IsNotNull() {
            addCriterion("前台交易码 is not null");
            return (Criteria) this;
        }

        public Criteria and前台交易码EqualTo(String value) {
            addCriterion("前台交易码 =", value, "前台交易码");
            return (Criteria) this;
        }

        public Criteria and前台交易码NotEqualTo(String value) {
            addCriterion("前台交易码 <>", value, "前台交易码");
            return (Criteria) this;
        }

        public Criteria and前台交易码GreaterThan(String value) {
            addCriterion("前台交易码 >", value, "前台交易码");
            return (Criteria) this;
        }

        public Criteria and前台交易码GreaterThanOrEqualTo(String value) {
            addCriterion("前台交易码 >=", value, "前台交易码");
            return (Criteria) this;
        }

        public Criteria and前台交易码LessThan(String value) {
            addCriterion("前台交易码 <", value, "前台交易码");
            return (Criteria) this;
        }

        public Criteria and前台交易码LessThanOrEqualTo(String value) {
            addCriterion("前台交易码 <=", value, "前台交易码");
            return (Criteria) this;
        }

        public Criteria and前台交易码Like(String value) {
            addCriterion("前台交易码 like", value, "前台交易码");
            return (Criteria) this;
        }

        public Criteria and前台交易码NotLike(String value) {
            addCriterion("前台交易码 not like", value, "前台交易码");
            return (Criteria) this;
        }

        public Criteria and前台交易码In(List<String> values) {
            addCriterion("前台交易码 in", values, "前台交易码");
            return (Criteria) this;
        }

        public Criteria and前台交易码NotIn(List<String> values) {
            addCriterion("前台交易码 not in", values, "前台交易码");
            return (Criteria) this;
        }

        public Criteria and前台交易码Between(String value1, String value2) {
            addCriterion("前台交易码 between", value1, value2, "前台交易码");
            return (Criteria) this;
        }

        public Criteria and前台交易码NotBetween(String value1, String value2) {
            addCriterion("前台交易码 not between", value1, value2, "前台交易码");
            return (Criteria) this;
        }

        public Criteria and后台日期IsNull() {
            addCriterion("后台日期 is null");
            return (Criteria) this;
        }

        public Criteria and后台日期IsNotNull() {
            addCriterion("后台日期 is not null");
            return (Criteria) this;
        }

        public Criteria and后台日期EqualTo(Date value) {
            addCriterionForJDBCDate("后台日期 =", value, "后台日期");
            return (Criteria) this;
        }

        public Criteria and后台日期NotEqualTo(Date value) {
            addCriterionForJDBCDate("后台日期 <>", value, "后台日期");
            return (Criteria) this;
        }

        public Criteria and后台日期GreaterThan(Date value) {
            addCriterionForJDBCDate("后台日期 >", value, "后台日期");
            return (Criteria) this;
        }

        public Criteria and后台日期GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("后台日期 >=", value, "后台日期");
            return (Criteria) this;
        }

        public Criteria and后台日期LessThan(Date value) {
            addCriterionForJDBCDate("后台日期 <", value, "后台日期");
            return (Criteria) this;
        }

        public Criteria and后台日期LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("后台日期 <=", value, "后台日期");
            return (Criteria) this;
        }

        public Criteria and后台日期In(List<Date> values) {
            addCriterionForJDBCDate("后台日期 in", values, "后台日期");
            return (Criteria) this;
        }

        public Criteria and后台日期NotIn(List<Date> values) {
            addCriterionForJDBCDate("后台日期 not in", values, "后台日期");
            return (Criteria) this;
        }

        public Criteria and后台日期Between(Date value1, Date value2) {
            addCriterionForJDBCDate("后台日期 between", value1, value2, "后台日期");
            return (Criteria) this;
        }

        public Criteria and后台日期NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("后台日期 not between", value1, value2, "后台日期");
            return (Criteria) this;
        }

        public Criteria and后台流水IsNull() {
            addCriterion("后台流水 is null");
            return (Criteria) this;
        }

        public Criteria and后台流水IsNotNull() {
            addCriterion("后台流水 is not null");
            return (Criteria) this;
        }

        public Criteria and后台流水EqualTo(String value) {
            addCriterion("后台流水 =", value, "后台流水");
            return (Criteria) this;
        }

        public Criteria and后台流水NotEqualTo(String value) {
            addCriterion("后台流水 <>", value, "后台流水");
            return (Criteria) this;
        }

        public Criteria and后台流水GreaterThan(String value) {
            addCriterion("后台流水 >", value, "后台流水");
            return (Criteria) this;
        }

        public Criteria and后台流水GreaterThanOrEqualTo(String value) {
            addCriterion("后台流水 >=", value, "后台流水");
            return (Criteria) this;
        }

        public Criteria and后台流水LessThan(String value) {
            addCriterion("后台流水 <", value, "后台流水");
            return (Criteria) this;
        }

        public Criteria and后台流水LessThanOrEqualTo(String value) {
            addCriterion("后台流水 <=", value, "后台流水");
            return (Criteria) this;
        }

        public Criteria and后台流水Like(String value) {
            addCriterion("后台流水 like", value, "后台流水");
            return (Criteria) this;
        }

        public Criteria and后台流水NotLike(String value) {
            addCriterion("后台流水 not like", value, "后台流水");
            return (Criteria) this;
        }

        public Criteria and后台流水In(List<String> values) {
            addCriterion("后台流水 in", values, "后台流水");
            return (Criteria) this;
        }

        public Criteria and后台流水NotIn(List<String> values) {
            addCriterion("后台流水 not in", values, "后台流水");
            return (Criteria) this;
        }

        public Criteria and后台流水Between(String value1, String value2) {
            addCriterion("后台流水 between", value1, value2, "后台流水");
            return (Criteria) this;
        }

        public Criteria and后台流水NotBetween(String value1, String value2) {
            addCriterion("后台流水 not between", value1, value2, "后台流水");
            return (Criteria) this;
        }

        public Criteria and后台交易码IsNull() {
            addCriterion("后台交易码 is null");
            return (Criteria) this;
        }

        public Criteria and后台交易码IsNotNull() {
            addCriterion("后台交易码 is not null");
            return (Criteria) this;
        }

        public Criteria and后台交易码EqualTo(String value) {
            addCriterion("后台交易码 =", value, "后台交易码");
            return (Criteria) this;
        }

        public Criteria and后台交易码NotEqualTo(String value) {
            addCriterion("后台交易码 <>", value, "后台交易码");
            return (Criteria) this;
        }

        public Criteria and后台交易码GreaterThan(String value) {
            addCriterion("后台交易码 >", value, "后台交易码");
            return (Criteria) this;
        }

        public Criteria and后台交易码GreaterThanOrEqualTo(String value) {
            addCriterion("后台交易码 >=", value, "后台交易码");
            return (Criteria) this;
        }

        public Criteria and后台交易码LessThan(String value) {
            addCriterion("后台交易码 <", value, "后台交易码");
            return (Criteria) this;
        }

        public Criteria and后台交易码LessThanOrEqualTo(String value) {
            addCriterion("后台交易码 <=", value, "后台交易码");
            return (Criteria) this;
        }

        public Criteria and后台交易码Like(String value) {
            addCriterion("后台交易码 like", value, "后台交易码");
            return (Criteria) this;
        }

        public Criteria and后台交易码NotLike(String value) {
            addCriterion("后台交易码 not like", value, "后台交易码");
            return (Criteria) this;
        }

        public Criteria and后台交易码In(List<String> values) {
            addCriterion("后台交易码 in", values, "后台交易码");
            return (Criteria) this;
        }

        public Criteria and后台交易码NotIn(List<String> values) {
            addCriterion("后台交易码 not in", values, "后台交易码");
            return (Criteria) this;
        }

        public Criteria and后台交易码Between(String value1, String value2) {
            addCriterion("后台交易码 between", value1, value2, "后台交易码");
            return (Criteria) this;
        }

        public Criteria and后台交易码NotBetween(String value1, String value2) {
            addCriterion("后台交易码 not between", value1, value2, "后台交易码");
            return (Criteria) this;
        }

        public Criteria and交易状态IsNull() {
            addCriterion("交易状态 is null");
            return (Criteria) this;
        }

        public Criteria and交易状态IsNotNull() {
            addCriterion("交易状态 is not null");
            return (Criteria) this;
        }

        public Criteria and交易状态EqualTo(String value) {
            addCriterion("交易状态 =", value, "交易状态");
            return (Criteria) this;
        }

        public Criteria and交易状态NotEqualTo(String value) {
            addCriterion("交易状态 <>", value, "交易状态");
            return (Criteria) this;
        }

        public Criteria and交易状态GreaterThan(String value) {
            addCriterion("交易状态 >", value, "交易状态");
            return (Criteria) this;
        }

        public Criteria and交易状态GreaterThanOrEqualTo(String value) {
            addCriterion("交易状态 >=", value, "交易状态");
            return (Criteria) this;
        }

        public Criteria and交易状态LessThan(String value) {
            addCriterion("交易状态 <", value, "交易状态");
            return (Criteria) this;
        }

        public Criteria and交易状态LessThanOrEqualTo(String value) {
            addCriterion("交易状态 <=", value, "交易状态");
            return (Criteria) this;
        }

        public Criteria and交易状态Like(String value) {
            addCriterion("交易状态 like", value, "交易状态");
            return (Criteria) this;
        }

        public Criteria and交易状态NotLike(String value) {
            addCriterion("交易状态 not like", value, "交易状态");
            return (Criteria) this;
        }

        public Criteria and交易状态In(List<String> values) {
            addCriterion("交易状态 in", values, "交易状态");
            return (Criteria) this;
        }

        public Criteria and交易状态NotIn(List<String> values) {
            addCriterion("交易状态 not in", values, "交易状态");
            return (Criteria) this;
        }

        public Criteria and交易状态Between(String value1, String value2) {
            addCriterion("交易状态 between", value1, value2, "交易状态");
            return (Criteria) this;
        }

        public Criteria and交易状态NotBetween(String value1, String value2) {
            addCriterion("交易状态 not between", value1, value2, "交易状态");
            return (Criteria) this;
        }

        public Criteria and终端号IsNull() {
            addCriterion("终端号 is null");
            return (Criteria) this;
        }

        public Criteria and终端号IsNotNull() {
            addCriterion("终端号 is not null");
            return (Criteria) this;
        }

        public Criteria and终端号EqualTo(String value) {
            addCriterion("终端号 =", value, "终端号");
            return (Criteria) this;
        }

        public Criteria and终端号NotEqualTo(String value) {
            addCriterion("终端号 <>", value, "终端号");
            return (Criteria) this;
        }

        public Criteria and终端号GreaterThan(String value) {
            addCriterion("终端号 >", value, "终端号");
            return (Criteria) this;
        }

        public Criteria and终端号GreaterThanOrEqualTo(String value) {
            addCriterion("终端号 >=", value, "终端号");
            return (Criteria) this;
        }

        public Criteria and终端号LessThan(String value) {
            addCriterion("终端号 <", value, "终端号");
            return (Criteria) this;
        }

        public Criteria and终端号LessThanOrEqualTo(String value) {
            addCriterion("终端号 <=", value, "终端号");
            return (Criteria) this;
        }

        public Criteria and终端号Like(String value) {
            addCriterion("终端号 like", value, "终端号");
            return (Criteria) this;
        }

        public Criteria and终端号NotLike(String value) {
            addCriterion("终端号 not like", value, "终端号");
            return (Criteria) this;
        }

        public Criteria and终端号In(List<String> values) {
            addCriterion("终端号 in", values, "终端号");
            return (Criteria) this;
        }

        public Criteria and终端号NotIn(List<String> values) {
            addCriterion("终端号 not in", values, "终端号");
            return (Criteria) this;
        }

        public Criteria and终端号Between(String value1, String value2) {
            addCriterion("终端号 between", value1, value2, "终端号");
            return (Criteria) this;
        }

        public Criteria and终端号NotBetween(String value1, String value2) {
            addCriterion("终端号 not between", value1, value2, "终端号");
            return (Criteria) this;
        }

        public Criteria andIP地址IsNull() {
            addCriterion("IP地址 is null");
            return (Criteria) this;
        }

        public Criteria andIP地址IsNotNull() {
            addCriterion("IP地址 is not null");
            return (Criteria) this;
        }

        public Criteria andIP地址EqualTo(String value) {
            addCriterion("IP地址 =", value, "IP地址");
            return (Criteria) this;
        }

        public Criteria andIP地址NotEqualTo(String value) {
            addCriterion("IP地址 <>", value, "IP地址");
            return (Criteria) this;
        }

        public Criteria andIP地址GreaterThan(String value) {
            addCriterion("IP地址 >", value, "IP地址");
            return (Criteria) this;
        }

        public Criteria andIP地址GreaterThanOrEqualTo(String value) {
            addCriterion("IP地址 >=", value, "IP地址");
            return (Criteria) this;
        }

        public Criteria andIP地址LessThan(String value) {
            addCriterion("IP地址 <", value, "IP地址");
            return (Criteria) this;
        }

        public Criteria andIP地址LessThanOrEqualTo(String value) {
            addCriterion("IP地址 <=", value, "IP地址");
            return (Criteria) this;
        }

        public Criteria andIP地址Like(String value) {
            addCriterion("IP地址 like", value, "IP地址");
            return (Criteria) this;
        }

        public Criteria andIP地址NotLike(String value) {
            addCriterion("IP地址 not like", value, "IP地址");
            return (Criteria) this;
        }

        public Criteria andIP地址In(List<String> values) {
            addCriterion("IP地址 in", values, "IP地址");
            return (Criteria) this;
        }

        public Criteria andIP地址NotIn(List<String> values) {
            addCriterion("IP地址 not in", values, "IP地址");
            return (Criteria) this;
        }

        public Criteria andIP地址Between(String value1, String value2) {
            addCriterion("IP地址 between", value1, value2, "IP地址");
            return (Criteria) this;
        }

        public Criteria andIP地址NotBetween(String value1, String value2) {
            addCriterion("IP地址 not between", value1, value2, "IP地址");
            return (Criteria) this;
        }

        public Criteria and时间戳IsNull() {
            addCriterion("时间戳 is null");
            return (Criteria) this;
        }

        public Criteria and时间戳IsNotNull() {
            addCriterion("时间戳 is not null");
            return (Criteria) this;
        }

        public Criteria and时间戳EqualTo(Date value) {
            addCriterion("时间戳 =", value, "时间戳");
            return (Criteria) this;
        }

        public Criteria and时间戳NotEqualTo(Date value) {
            addCriterion("时间戳 <>", value, "时间戳");
            return (Criteria) this;
        }

        public Criteria and时间戳GreaterThan(Date value) {
            addCriterion("时间戳 >", value, "时间戳");
            return (Criteria) this;
        }

        public Criteria and时间戳GreaterThanOrEqualTo(Date value) {
            addCriterion("时间戳 >=", value, "时间戳");
            return (Criteria) this;
        }

        public Criteria and时间戳LessThan(Date value) {
            addCriterion("时间戳 <", value, "时间戳");
            return (Criteria) this;
        }

        public Criteria and时间戳LessThanOrEqualTo(Date value) {
            addCriterion("时间戳 <=", value, "时间戳");
            return (Criteria) this;
        }

        public Criteria and时间戳In(List<Date> values) {
            addCriterion("时间戳 in", values, "时间戳");
            return (Criteria) this;
        }

        public Criteria and时间戳NotIn(List<Date> values) {
            addCriterion("时间戳 not in", values, "时间戳");
            return (Criteria) this;
        }

        public Criteria and时间戳Between(Date value1, Date value2) {
            addCriterion("时间戳 between", value1, value2, "时间戳");
            return (Criteria) this;
        }

        public Criteria and时间戳NotBetween(Date value1, Date value2) {
            addCriterion("时间戳 not between", value1, value2, "时间戳");
            return (Criteria) this;
        }

        public Criteria and记录状态IsNull() {
            addCriterion("记录状态 is null");
            return (Criteria) this;
        }

        public Criteria and记录状态IsNotNull() {
            addCriterion("记录状态 is not null");
            return (Criteria) this;
        }

        public Criteria and记录状态EqualTo(String value) {
            addCriterion("记录状态 =", value, "记录状态");
            return (Criteria) this;
        }

        public Criteria and记录状态NotEqualTo(String value) {
            addCriterion("记录状态 <>", value, "记录状态");
            return (Criteria) this;
        }

        public Criteria and记录状态GreaterThan(String value) {
            addCriterion("记录状态 >", value, "记录状态");
            return (Criteria) this;
        }

        public Criteria and记录状态GreaterThanOrEqualTo(String value) {
            addCriterion("记录状态 >=", value, "记录状态");
            return (Criteria) this;
        }

        public Criteria and记录状态LessThan(String value) {
            addCriterion("记录状态 <", value, "记录状态");
            return (Criteria) this;
        }

        public Criteria and记录状态LessThanOrEqualTo(String value) {
            addCriterion("记录状态 <=", value, "记录状态");
            return (Criteria) this;
        }

        public Criteria and记录状态Like(String value) {
            addCriterion("记录状态 like", value, "记录状态");
            return (Criteria) this;
        }

        public Criteria and记录状态NotLike(String value) {
            addCriterion("记录状态 not like", value, "记录状态");
            return (Criteria) this;
        }

        public Criteria and记录状态In(List<String> values) {
            addCriterion("记录状态 in", values, "记录状态");
            return (Criteria) this;
        }

        public Criteria and记录状态NotIn(List<String> values) {
            addCriterion("记录状态 not in", values, "记录状态");
            return (Criteria) this;
        }

        public Criteria and记录状态Between(String value1, String value2) {
            addCriterion("记录状态 between", value1, value2, "记录状态");
            return (Criteria) this;
        }

        public Criteria and记录状态NotBetween(String value1, String value2) {
            addCriterion("记录状态 not between", value1, value2, "记录状态");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}