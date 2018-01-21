package XQBHServer.Server.Table.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MDZSJExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MDZSJExample() {
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

        protected void addCriterionForJDBCTime(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value.getTime()), property);
        }

        protected void addCriterionForJDBCTime(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Time> timeList = new ArrayList<java.sql.Time>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                timeList.add(new java.sql.Time(iter.next().getTime()));
            }
            addCriterion(condition, timeList, property);
        }

        protected void addCriterionForJDBCTime(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value1.getTime()), new java.sql.Time(value2.getTime()), property);
        }

        public Criteria andFRDM_UIsNull() {
            addCriterion("FRDM_U is null");
            return (Criteria) this;
        }

        public Criteria andFRDM_UIsNotNull() {
            addCriterion("FRDM_U is not null");
            return (Criteria) this;
        }

        public Criteria andFRDM_UEqualTo(String value) {
            addCriterion("FRDM_U =", value, "FRDM_U");
            return (Criteria) this;
        }

        public Criteria andFRDM_UNotEqualTo(String value) {
            addCriterion("FRDM_U <>", value, "FRDM_U");
            return (Criteria) this;
        }

        public Criteria andFRDM_UGreaterThan(String value) {
            addCriterion("FRDM_U >", value, "FRDM_U");
            return (Criteria) this;
        }

        public Criteria andFRDM_UGreaterThanOrEqualTo(String value) {
            addCriterion("FRDM_U >=", value, "FRDM_U");
            return (Criteria) this;
        }

        public Criteria andFRDM_ULessThan(String value) {
            addCriterion("FRDM_U <", value, "FRDM_U");
            return (Criteria) this;
        }

        public Criteria andFRDM_ULessThanOrEqualTo(String value) {
            addCriterion("FRDM_U <=", value, "FRDM_U");
            return (Criteria) this;
        }

        public Criteria andFRDM_ULike(String value) {
            addCriterion("FRDM_U like", value, "FRDM_U");
            return (Criteria) this;
        }

        public Criteria andFRDM_UNotLike(String value) {
            addCriterion("FRDM_U not like", value, "FRDM_U");
            return (Criteria) this;
        }

        public Criteria andFRDM_UIn(List<String> values) {
            addCriterion("FRDM_U in", values, "FRDM_U");
            return (Criteria) this;
        }

        public Criteria andFRDM_UNotIn(List<String> values) {
            addCriterion("FRDM_U not in", values, "FRDM_U");
            return (Criteria) this;
        }

        public Criteria andFRDM_UBetween(String value1, String value2) {
            addCriterion("FRDM_U between", value1, value2, "FRDM_U");
            return (Criteria) this;
        }

        public Criteria andFRDM_UNotBetween(String value1, String value2) {
            addCriterion("FRDM_U not between", value1, value2, "FRDM_U");
            return (Criteria) this;
        }

        public Criteria andHTRQ_UIsNull() {
            addCriterion("HTRQ_U is null");
            return (Criteria) this;
        }

        public Criteria andHTRQ_UIsNotNull() {
            addCriterion("HTRQ_U is not null");
            return (Criteria) this;
        }

        public Criteria andHTRQ_UEqualTo(Date value) {
            addCriterionForJDBCDate("HTRQ_U =", value, "HTRQ_U");
            return (Criteria) this;
        }

        public Criteria andHTRQ_UNotEqualTo(Date value) {
            addCriterionForJDBCDate("HTRQ_U <>", value, "HTRQ_U");
            return (Criteria) this;
        }

        public Criteria andHTRQ_UGreaterThan(Date value) {
            addCriterionForJDBCDate("HTRQ_U >", value, "HTRQ_U");
            return (Criteria) this;
        }

        public Criteria andHTRQ_UGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("HTRQ_U >=", value, "HTRQ_U");
            return (Criteria) this;
        }

        public Criteria andHTRQ_ULessThan(Date value) {
            addCriterionForJDBCDate("HTRQ_U <", value, "HTRQ_U");
            return (Criteria) this;
        }

        public Criteria andHTRQ_ULessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("HTRQ_U <=", value, "HTRQ_U");
            return (Criteria) this;
        }

        public Criteria andHTRQ_UIn(List<Date> values) {
            addCriterionForJDBCDate("HTRQ_U in", values, "HTRQ_U");
            return (Criteria) this;
        }

        public Criteria andHTRQ_UNotIn(List<Date> values) {
            addCriterionForJDBCDate("HTRQ_U not in", values, "HTRQ_U");
            return (Criteria) this;
        }

        public Criteria andHTRQ_UBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("HTRQ_U between", value1, value2, "HTRQ_U");
            return (Criteria) this;
        }

        public Criteria andHTRQ_UNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("HTRQ_U not between", value1, value2, "HTRQ_U");
            return (Criteria) this;
        }

        public Criteria andHTLS_UIsNull() {
            addCriterion("HTLS_U is null");
            return (Criteria) this;
        }

        public Criteria andHTLS_UIsNotNull() {
            addCriterion("HTLS_U is not null");
            return (Criteria) this;
        }

        public Criteria andHTLS_UEqualTo(String value) {
            addCriterion("HTLS_U =", value, "HTLS_U");
            return (Criteria) this;
        }

        public Criteria andHTLS_UNotEqualTo(String value) {
            addCriterion("HTLS_U <>", value, "HTLS_U");
            return (Criteria) this;
        }

        public Criteria andHTLS_UGreaterThan(String value) {
            addCriterion("HTLS_U >", value, "HTLS_U");
            return (Criteria) this;
        }

        public Criteria andHTLS_UGreaterThanOrEqualTo(String value) {
            addCriterion("HTLS_U >=", value, "HTLS_U");
            return (Criteria) this;
        }

        public Criteria andHTLS_ULessThan(String value) {
            addCriterion("HTLS_U <", value, "HTLS_U");
            return (Criteria) this;
        }

        public Criteria andHTLS_ULessThanOrEqualTo(String value) {
            addCriterion("HTLS_U <=", value, "HTLS_U");
            return (Criteria) this;
        }

        public Criteria andHTLS_ULike(String value) {
            addCriterion("HTLS_U like", value, "HTLS_U");
            return (Criteria) this;
        }

        public Criteria andHTLS_UNotLike(String value) {
            addCriterion("HTLS_U not like", value, "HTLS_U");
            return (Criteria) this;
        }

        public Criteria andHTLS_UIn(List<String> values) {
            addCriterion("HTLS_U in", values, "HTLS_U");
            return (Criteria) this;
        }

        public Criteria andHTLS_UNotIn(List<String> values) {
            addCriterion("HTLS_U not in", values, "HTLS_U");
            return (Criteria) this;
        }

        public Criteria andHTLS_UBetween(String value1, String value2) {
            addCriterion("HTLS_U between", value1, value2, "HTLS_U");
            return (Criteria) this;
        }

        public Criteria andHTLS_UNotBetween(String value1, String value2) {
            addCriterion("HTLS_U not between", value1, value2, "HTLS_U");
            return (Criteria) this;
        }

        public Criteria andJYSJ_UIsNull() {
            addCriterion("JYSJ_U is null");
            return (Criteria) this;
        }

        public Criteria andJYSJ_UIsNotNull() {
            addCriterion("JYSJ_U is not null");
            return (Criteria) this;
        }

        public Criteria andJYSJ_UEqualTo(Date value) {
            addCriterionForJDBCTime("JYSJ_U =", value, "JYSJ_U");
            return (Criteria) this;
        }

        public Criteria andJYSJ_UNotEqualTo(Date value) {
            addCriterionForJDBCTime("JYSJ_U <>", value, "JYSJ_U");
            return (Criteria) this;
        }

        public Criteria andJYSJ_UGreaterThan(Date value) {
            addCriterionForJDBCTime("JYSJ_U >", value, "JYSJ_U");
            return (Criteria) this;
        }

        public Criteria andJYSJ_UGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("JYSJ_U >=", value, "JYSJ_U");
            return (Criteria) this;
        }

        public Criteria andJYSJ_ULessThan(Date value) {
            addCriterionForJDBCTime("JYSJ_U <", value, "JYSJ_U");
            return (Criteria) this;
        }

        public Criteria andJYSJ_ULessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("JYSJ_U <=", value, "JYSJ_U");
            return (Criteria) this;
        }

        public Criteria andJYSJ_UIn(List<Date> values) {
            addCriterionForJDBCTime("JYSJ_U in", values, "JYSJ_U");
            return (Criteria) this;
        }

        public Criteria andJYSJ_UNotIn(List<Date> values) {
            addCriterionForJDBCTime("JYSJ_U not in", values, "JYSJ_U");
            return (Criteria) this;
        }

        public Criteria andJYSJ_UBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("JYSJ_U between", value1, value2, "JYSJ_U");
            return (Criteria) this;
        }

        public Criteria andJYSJ_UNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("JYSJ_U not between", value1, value2, "JYSJ_U");
            return (Criteria) this;
        }

        public Criteria andHTJYM_IsNull() {
            addCriterion("HTJYM_ is null");
            return (Criteria) this;
        }

        public Criteria andHTJYM_IsNotNull() {
            addCriterion("HTJYM_ is not null");
            return (Criteria) this;
        }

        public Criteria andHTJYM_EqualTo(String value) {
            addCriterion("HTJYM_ =", value, "HTJYM_");
            return (Criteria) this;
        }

        public Criteria andHTJYM_NotEqualTo(String value) {
            addCriterion("HTJYM_ <>", value, "HTJYM_");
            return (Criteria) this;
        }

        public Criteria andHTJYM_GreaterThan(String value) {
            addCriterion("HTJYM_ >", value, "HTJYM_");
            return (Criteria) this;
        }

        public Criteria andHTJYM_GreaterThanOrEqualTo(String value) {
            addCriterion("HTJYM_ >=", value, "HTJYM_");
            return (Criteria) this;
        }

        public Criteria andHTJYM_LessThan(String value) {
            addCriterion("HTJYM_ <", value, "HTJYM_");
            return (Criteria) this;
        }

        public Criteria andHTJYM_LessThanOrEqualTo(String value) {
            addCriterion("HTJYM_ <=", value, "HTJYM_");
            return (Criteria) this;
        }

        public Criteria andHTJYM_Like(String value) {
            addCriterion("HTJYM_ like", value, "HTJYM_");
            return (Criteria) this;
        }

        public Criteria andHTJYM_NotLike(String value) {
            addCriterion("HTJYM_ not like", value, "HTJYM_");
            return (Criteria) this;
        }

        public Criteria andHTJYM_In(List<String> values) {
            addCriterion("HTJYM_ in", values, "HTJYM_");
            return (Criteria) this;
        }

        public Criteria andHTJYM_NotIn(List<String> values) {
            addCriterion("HTJYM_ not in", values, "HTJYM_");
            return (Criteria) this;
        }

        public Criteria andHTJYM_Between(String value1, String value2) {
            addCriterion("HTJYM_ between", value1, value2, "HTJYM_");
            return (Criteria) this;
        }

        public Criteria andHTJYM_NotBetween(String value1, String value2) {
            addCriterion("HTJYM_ not between", value1, value2, "HTJYM_");
            return (Criteria) this;
        }

        public Criteria andJYLX_UIsNull() {
            addCriterion("JYLX_U is null");
            return (Criteria) this;
        }

        public Criteria andJYLX_UIsNotNull() {
            addCriterion("JYLX_U is not null");
            return (Criteria) this;
        }

        public Criteria andJYLX_UEqualTo(String value) {
            addCriterion("JYLX_U =", value, "JYLX_U");
            return (Criteria) this;
        }

        public Criteria andJYLX_UNotEqualTo(String value) {
            addCriterion("JYLX_U <>", value, "JYLX_U");
            return (Criteria) this;
        }

        public Criteria andJYLX_UGreaterThan(String value) {
            addCriterion("JYLX_U >", value, "JYLX_U");
            return (Criteria) this;
        }

        public Criteria andJYLX_UGreaterThanOrEqualTo(String value) {
            addCriterion("JYLX_U >=", value, "JYLX_U");
            return (Criteria) this;
        }

        public Criteria andJYLX_ULessThan(String value) {
            addCriterion("JYLX_U <", value, "JYLX_U");
            return (Criteria) this;
        }

        public Criteria andJYLX_ULessThanOrEqualTo(String value) {
            addCriterion("JYLX_U <=", value, "JYLX_U");
            return (Criteria) this;
        }

        public Criteria andJYLX_ULike(String value) {
            addCriterion("JYLX_U like", value, "JYLX_U");
            return (Criteria) this;
        }

        public Criteria andJYLX_UNotLike(String value) {
            addCriterion("JYLX_U not like", value, "JYLX_U");
            return (Criteria) this;
        }

        public Criteria andJYLX_UIn(List<String> values) {
            addCriterion("JYLX_U in", values, "JYLX_U");
            return (Criteria) this;
        }

        public Criteria andJYLX_UNotIn(List<String> values) {
            addCriterion("JYLX_U not in", values, "JYLX_U");
            return (Criteria) this;
        }

        public Criteria andJYLX_UBetween(String value1, String value2) {
            addCriterion("JYLX_U between", value1, value2, "JYLX_U");
            return (Criteria) this;
        }

        public Criteria andJYLX_UNotBetween(String value1, String value2) {
            addCriterion("JYLX_U not between", value1, value2, "JYLX_U");
            return (Criteria) this;
        }

        public Criteria andJYJE_UIsNull() {
            addCriterion("JYJE_U is null");
            return (Criteria) this;
        }

        public Criteria andJYJE_UIsNotNull() {
            addCriterion("JYJE_U is not null");
            return (Criteria) this;
        }

        public Criteria andJYJE_UEqualTo(BigDecimal value) {
            addCriterion("JYJE_U =", value, "JYJE_U");
            return (Criteria) this;
        }

        public Criteria andJYJE_UNotEqualTo(BigDecimal value) {
            addCriterion("JYJE_U <>", value, "JYJE_U");
            return (Criteria) this;
        }

        public Criteria andJYJE_UGreaterThan(BigDecimal value) {
            addCriterion("JYJE_U >", value, "JYJE_U");
            return (Criteria) this;
        }

        public Criteria andJYJE_UGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("JYJE_U >=", value, "JYJE_U");
            return (Criteria) this;
        }

        public Criteria andJYJE_ULessThan(BigDecimal value) {
            addCriterion("JYJE_U <", value, "JYJE_U");
            return (Criteria) this;
        }

        public Criteria andJYJE_ULessThanOrEqualTo(BigDecimal value) {
            addCriterion("JYJE_U <=", value, "JYJE_U");
            return (Criteria) this;
        }

        public Criteria andJYJE_UIn(List<BigDecimal> values) {
            addCriterion("JYJE_U in", values, "JYJE_U");
            return (Criteria) this;
        }

        public Criteria andJYJE_UNotIn(List<BigDecimal> values) {
            addCriterion("JYJE_U not in", values, "JYJE_U");
            return (Criteria) this;
        }

        public Criteria andJYJE_UBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("JYJE_U between", value1, value2, "JYJE_U");
            return (Criteria) this;
        }

        public Criteria andJYJE_UNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("JYJE_U not between", value1, value2, "JYJE_U");
            return (Criteria) this;
        }

        public Criteria andSHBH_UIsNull() {
            addCriterion("SHBH_U is null");
            return (Criteria) this;
        }

        public Criteria andSHBH_UIsNotNull() {
            addCriterion("SHBH_U is not null");
            return (Criteria) this;
        }

        public Criteria andSHBH_UEqualTo(String value) {
            addCriterion("SHBH_U =", value, "SHBH_U");
            return (Criteria) this;
        }

        public Criteria andSHBH_UNotEqualTo(String value) {
            addCriterion("SHBH_U <>", value, "SHBH_U");
            return (Criteria) this;
        }

        public Criteria andSHBH_UGreaterThan(String value) {
            addCriterion("SHBH_U >", value, "SHBH_U");
            return (Criteria) this;
        }

        public Criteria andSHBH_UGreaterThanOrEqualTo(String value) {
            addCriterion("SHBH_U >=", value, "SHBH_U");
            return (Criteria) this;
        }

        public Criteria andSHBH_ULessThan(String value) {
            addCriterion("SHBH_U <", value, "SHBH_U");
            return (Criteria) this;
        }

        public Criteria andSHBH_ULessThanOrEqualTo(String value) {
            addCriterion("SHBH_U <=", value, "SHBH_U");
            return (Criteria) this;
        }

        public Criteria andSHBH_ULike(String value) {
            addCriterion("SHBH_U like", value, "SHBH_U");
            return (Criteria) this;
        }

        public Criteria andSHBH_UNotLike(String value) {
            addCriterion("SHBH_U not like", value, "SHBH_U");
            return (Criteria) this;
        }

        public Criteria andSHBH_UIn(List<String> values) {
            addCriterion("SHBH_U in", values, "SHBH_U");
            return (Criteria) this;
        }

        public Criteria andSHBH_UNotIn(List<String> values) {
            addCriterion("SHBH_U not in", values, "SHBH_U");
            return (Criteria) this;
        }

        public Criteria andSHBH_UBetween(String value1, String value2) {
            addCriterion("SHBH_U between", value1, value2, "SHBH_U");
            return (Criteria) this;
        }

        public Criteria andSHBH_UNotBetween(String value1, String value2) {
            addCriterion("SHBH_U not between", value1, value2, "SHBH_U");
            return (Criteria) this;
        }

        public Criteria andZDBH_UIsNull() {
            addCriterion("ZDBH_U is null");
            return (Criteria) this;
        }

        public Criteria andZDBH_UIsNotNull() {
            addCriterion("ZDBH_U is not null");
            return (Criteria) this;
        }

        public Criteria andZDBH_UEqualTo(String value) {
            addCriterion("ZDBH_U =", value, "ZDBH_U");
            return (Criteria) this;
        }

        public Criteria andZDBH_UNotEqualTo(String value) {
            addCriterion("ZDBH_U <>", value, "ZDBH_U");
            return (Criteria) this;
        }

        public Criteria andZDBH_UGreaterThan(String value) {
            addCriterion("ZDBH_U >", value, "ZDBH_U");
            return (Criteria) this;
        }

        public Criteria andZDBH_UGreaterThanOrEqualTo(String value) {
            addCriterion("ZDBH_U >=", value, "ZDBH_U");
            return (Criteria) this;
        }

        public Criteria andZDBH_ULessThan(String value) {
            addCriterion("ZDBH_U <", value, "ZDBH_U");
            return (Criteria) this;
        }

        public Criteria andZDBH_ULessThanOrEqualTo(String value) {
            addCriterion("ZDBH_U <=", value, "ZDBH_U");
            return (Criteria) this;
        }

        public Criteria andZDBH_ULike(String value) {
            addCriterion("ZDBH_U like", value, "ZDBH_U");
            return (Criteria) this;
        }

        public Criteria andZDBH_UNotLike(String value) {
            addCriterion("ZDBH_U not like", value, "ZDBH_U");
            return (Criteria) this;
        }

        public Criteria andZDBH_UIn(List<String> values) {
            addCriterion("ZDBH_U in", values, "ZDBH_U");
            return (Criteria) this;
        }

        public Criteria andZDBH_UNotIn(List<String> values) {
            addCriterion("ZDBH_U not in", values, "ZDBH_U");
            return (Criteria) this;
        }

        public Criteria andZDBH_UBetween(String value1, String value2) {
            addCriterion("ZDBH_U between", value1, value2, "ZDBH_U");
            return (Criteria) this;
        }

        public Criteria andZDBH_UNotBetween(String value1, String value2) {
            addCriterion("ZDBH_U not between", value1, value2, "ZDBH_U");
            return (Criteria) this;
        }

        public Criteria andZFZHLXIsNull() {
            addCriterion("ZFZHLX is null");
            return (Criteria) this;
        }

        public Criteria andZFZHLXIsNotNull() {
            addCriterion("ZFZHLX is not null");
            return (Criteria) this;
        }

        public Criteria andZFZHLXEqualTo(String value) {
            addCriterion("ZFZHLX =", value, "ZFZHLX");
            return (Criteria) this;
        }

        public Criteria andZFZHLXNotEqualTo(String value) {
            addCriterion("ZFZHLX <>", value, "ZFZHLX");
            return (Criteria) this;
        }

        public Criteria andZFZHLXGreaterThan(String value) {
            addCriterion("ZFZHLX >", value, "ZFZHLX");
            return (Criteria) this;
        }

        public Criteria andZFZHLXGreaterThanOrEqualTo(String value) {
            addCriterion("ZFZHLX >=", value, "ZFZHLX");
            return (Criteria) this;
        }

        public Criteria andZFZHLXLessThan(String value) {
            addCriterion("ZFZHLX <", value, "ZFZHLX");
            return (Criteria) this;
        }

        public Criteria andZFZHLXLessThanOrEqualTo(String value) {
            addCriterion("ZFZHLX <=", value, "ZFZHLX");
            return (Criteria) this;
        }

        public Criteria andZFZHLXLike(String value) {
            addCriterion("ZFZHLX like", value, "ZFZHLX");
            return (Criteria) this;
        }

        public Criteria andZFZHLXNotLike(String value) {
            addCriterion("ZFZHLX not like", value, "ZFZHLX");
            return (Criteria) this;
        }

        public Criteria andZFZHLXIn(List<String> values) {
            addCriterion("ZFZHLX in", values, "ZFZHLX");
            return (Criteria) this;
        }

        public Criteria andZFZHLXNotIn(List<String> values) {
            addCriterion("ZFZHLX not in", values, "ZFZHLX");
            return (Criteria) this;
        }

        public Criteria andZFZHLXBetween(String value1, String value2) {
            addCriterion("ZFZHLX between", value1, value2, "ZFZHLX");
            return (Criteria) this;
        }

        public Criteria andZFZHLXNotBetween(String value1, String value2) {
            addCriterion("ZFZHLX not between", value1, value2, "ZFZHLX");
            return (Criteria) this;
        }

        public Criteria andSFDH_UIsNull() {
            addCriterion("SFDH_U is null");
            return (Criteria) this;
        }

        public Criteria andSFDH_UIsNotNull() {
            addCriterion("SFDH_U is not null");
            return (Criteria) this;
        }

        public Criteria andSFDH_UEqualTo(String value) {
            addCriterion("SFDH_U =", value, "SFDH_U");
            return (Criteria) this;
        }

        public Criteria andSFDH_UNotEqualTo(String value) {
            addCriterion("SFDH_U <>", value, "SFDH_U");
            return (Criteria) this;
        }

        public Criteria andSFDH_UGreaterThan(String value) {
            addCriterion("SFDH_U >", value, "SFDH_U");
            return (Criteria) this;
        }

        public Criteria andSFDH_UGreaterThanOrEqualTo(String value) {
            addCriterion("SFDH_U >=", value, "SFDH_U");
            return (Criteria) this;
        }

        public Criteria andSFDH_ULessThan(String value) {
            addCriterion("SFDH_U <", value, "SFDH_U");
            return (Criteria) this;
        }

        public Criteria andSFDH_ULessThanOrEqualTo(String value) {
            addCriterion("SFDH_U <=", value, "SFDH_U");
            return (Criteria) this;
        }

        public Criteria andSFDH_ULike(String value) {
            addCriterion("SFDH_U like", value, "SFDH_U");
            return (Criteria) this;
        }

        public Criteria andSFDH_UNotLike(String value) {
            addCriterion("SFDH_U not like", value, "SFDH_U");
            return (Criteria) this;
        }

        public Criteria andSFDH_UIn(List<String> values) {
            addCriterion("SFDH_U in", values, "SFDH_U");
            return (Criteria) this;
        }

        public Criteria andSFDH_UNotIn(List<String> values) {
            addCriterion("SFDH_U not in", values, "SFDH_U");
            return (Criteria) this;
        }

        public Criteria andSFDH_UBetween(String value1, String value2) {
            addCriterion("SFDH_U between", value1, value2, "SFDH_U");
            return (Criteria) this;
        }

        public Criteria andSFDH_UNotBetween(String value1, String value2) {
            addCriterion("SFDH_U not between", value1, value2, "SFDH_U");
            return (Criteria) this;
        }

        public Criteria andSFLS_UIsNull() {
            addCriterion("SFLS_U is null");
            return (Criteria) this;
        }

        public Criteria andSFLS_UIsNotNull() {
            addCriterion("SFLS_U is not null");
            return (Criteria) this;
        }

        public Criteria andSFLS_UEqualTo(String value) {
            addCriterion("SFLS_U =", value, "SFLS_U");
            return (Criteria) this;
        }

        public Criteria andSFLS_UNotEqualTo(String value) {
            addCriterion("SFLS_U <>", value, "SFLS_U");
            return (Criteria) this;
        }

        public Criteria andSFLS_UGreaterThan(String value) {
            addCriterion("SFLS_U >", value, "SFLS_U");
            return (Criteria) this;
        }

        public Criteria andSFLS_UGreaterThanOrEqualTo(String value) {
            addCriterion("SFLS_U >=", value, "SFLS_U");
            return (Criteria) this;
        }

        public Criteria andSFLS_ULessThan(String value) {
            addCriterion("SFLS_U <", value, "SFLS_U");
            return (Criteria) this;
        }

        public Criteria andSFLS_ULessThanOrEqualTo(String value) {
            addCriterion("SFLS_U <=", value, "SFLS_U");
            return (Criteria) this;
        }

        public Criteria andSFLS_ULike(String value) {
            addCriterion("SFLS_U like", value, "SFLS_U");
            return (Criteria) this;
        }

        public Criteria andSFLS_UNotLike(String value) {
            addCriterion("SFLS_U not like", value, "SFLS_U");
            return (Criteria) this;
        }

        public Criteria andSFLS_UIn(List<String> values) {
            addCriterion("SFLS_U in", values, "SFLS_U");
            return (Criteria) this;
        }

        public Criteria andSFLS_UNotIn(List<String> values) {
            addCriterion("SFLS_U not in", values, "SFLS_U");
            return (Criteria) this;
        }

        public Criteria andSFLS_UBetween(String value1, String value2) {
            addCriterion("SFLS_U between", value1, value2, "SFLS_U");
            return (Criteria) this;
        }

        public Criteria andSFLS_UNotBetween(String value1, String value2) {
            addCriterion("SFLS_U not between", value1, value2, "SFLS_U");
            return (Criteria) this;
        }

        public Criteria andSFRQ_UIsNull() {
            addCriterion("SFRQ_U is null");
            return (Criteria) this;
        }

        public Criteria andSFRQ_UIsNotNull() {
            addCriterion("SFRQ_U is not null");
            return (Criteria) this;
        }

        public Criteria andSFRQ_UEqualTo(Date value) {
            addCriterionForJDBCDate("SFRQ_U =", value, "SFRQ_U");
            return (Criteria) this;
        }

        public Criteria andSFRQ_UNotEqualTo(Date value) {
            addCriterionForJDBCDate("SFRQ_U <>", value, "SFRQ_U");
            return (Criteria) this;
        }

        public Criteria andSFRQ_UGreaterThan(Date value) {
            addCriterionForJDBCDate("SFRQ_U >", value, "SFRQ_U");
            return (Criteria) this;
        }

        public Criteria andSFRQ_UGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SFRQ_U >=", value, "SFRQ_U");
            return (Criteria) this;
        }

        public Criteria andSFRQ_ULessThan(Date value) {
            addCriterionForJDBCDate("SFRQ_U <", value, "SFRQ_U");
            return (Criteria) this;
        }

        public Criteria andSFRQ_ULessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SFRQ_U <=", value, "SFRQ_U");
            return (Criteria) this;
        }

        public Criteria andSFRQ_UIn(List<Date> values) {
            addCriterionForJDBCDate("SFRQ_U in", values, "SFRQ_U");
            return (Criteria) this;
        }

        public Criteria andSFRQ_UNotIn(List<Date> values) {
            addCriterionForJDBCDate("SFRQ_U not in", values, "SFRQ_U");
            return (Criteria) this;
        }

        public Criteria andSFRQ_UBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SFRQ_U between", value1, value2, "SFRQ_U");
            return (Criteria) this;
        }

        public Criteria andSFRQ_UNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SFRQ_U not between", value1, value2, "SFRQ_U");
            return (Criteria) this;
        }

        public Criteria andFKRID_IsNull() {
            addCriterion("FKRID_ is null");
            return (Criteria) this;
        }

        public Criteria andFKRID_IsNotNull() {
            addCriterion("FKRID_ is not null");
            return (Criteria) this;
        }

        public Criteria andFKRID_EqualTo(String value) {
            addCriterion("FKRID_ =", value, "FKRID_");
            return (Criteria) this;
        }

        public Criteria andFKRID_NotEqualTo(String value) {
            addCriterion("FKRID_ <>", value, "FKRID_");
            return (Criteria) this;
        }

        public Criteria andFKRID_GreaterThan(String value) {
            addCriterion("FKRID_ >", value, "FKRID_");
            return (Criteria) this;
        }

        public Criteria andFKRID_GreaterThanOrEqualTo(String value) {
            addCriterion("FKRID_ >=", value, "FKRID_");
            return (Criteria) this;
        }

        public Criteria andFKRID_LessThan(String value) {
            addCriterion("FKRID_ <", value, "FKRID_");
            return (Criteria) this;
        }

        public Criteria andFKRID_LessThanOrEqualTo(String value) {
            addCriterion("FKRID_ <=", value, "FKRID_");
            return (Criteria) this;
        }

        public Criteria andFKRID_Like(String value) {
            addCriterion("FKRID_ like", value, "FKRID_");
            return (Criteria) this;
        }

        public Criteria andFKRID_NotLike(String value) {
            addCriterion("FKRID_ not like", value, "FKRID_");
            return (Criteria) this;
        }

        public Criteria andFKRID_In(List<String> values) {
            addCriterion("FKRID_ in", values, "FKRID_");
            return (Criteria) this;
        }

        public Criteria andFKRID_NotIn(List<String> values) {
            addCriterion("FKRID_ not in", values, "FKRID_");
            return (Criteria) this;
        }

        public Criteria andFKRID_Between(String value1, String value2) {
            addCriterion("FKRID_ between", value1, value2, "FKRID_");
            return (Criteria) this;
        }

        public Criteria andFKRID_NotBetween(String value1, String value2) {
            addCriterion("FKRID_ not between", value1, value2, "FKRID_");
            return (Criteria) this;
        }

        public Criteria andFKRZH_IsNull() {
            addCriterion("FKRZH_ is null");
            return (Criteria) this;
        }

        public Criteria andFKRZH_IsNotNull() {
            addCriterion("FKRZH_ is not null");
            return (Criteria) this;
        }

        public Criteria andFKRZH_EqualTo(String value) {
            addCriterion("FKRZH_ =", value, "FKRZH_");
            return (Criteria) this;
        }

        public Criteria andFKRZH_NotEqualTo(String value) {
            addCriterion("FKRZH_ <>", value, "FKRZH_");
            return (Criteria) this;
        }

        public Criteria andFKRZH_GreaterThan(String value) {
            addCriterion("FKRZH_ >", value, "FKRZH_");
            return (Criteria) this;
        }

        public Criteria andFKRZH_GreaterThanOrEqualTo(String value) {
            addCriterion("FKRZH_ >=", value, "FKRZH_");
            return (Criteria) this;
        }

        public Criteria andFKRZH_LessThan(String value) {
            addCriterion("FKRZH_ <", value, "FKRZH_");
            return (Criteria) this;
        }

        public Criteria andFKRZH_LessThanOrEqualTo(String value) {
            addCriterion("FKRZH_ <=", value, "FKRZH_");
            return (Criteria) this;
        }

        public Criteria andFKRZH_Like(String value) {
            addCriterion("FKRZH_ like", value, "FKRZH_");
            return (Criteria) this;
        }

        public Criteria andFKRZH_NotLike(String value) {
            addCriterion("FKRZH_ not like", value, "FKRZH_");
            return (Criteria) this;
        }

        public Criteria andFKRZH_In(List<String> values) {
            addCriterion("FKRZH_ in", values, "FKRZH_");
            return (Criteria) this;
        }

        public Criteria andFKRZH_NotIn(List<String> values) {
            addCriterion("FKRZH_ not in", values, "FKRZH_");
            return (Criteria) this;
        }

        public Criteria andFKRZH_Between(String value1, String value2) {
            addCriterion("FKRZH_ between", value1, value2, "FKRZH_");
            return (Criteria) this;
        }

        public Criteria andFKRZH_NotBetween(String value1, String value2) {
            addCriterion("FKRZH_ not between", value1, value2, "FKRZH_");
            return (Criteria) this;
        }

        public Criteria andQTRQ_UIsNull() {
            addCriterion("QTRQ_U is null");
            return (Criteria) this;
        }

        public Criteria andQTRQ_UIsNotNull() {
            addCriterion("QTRQ_U is not null");
            return (Criteria) this;
        }

        public Criteria andQTRQ_UEqualTo(Date value) {
            addCriterionForJDBCDate("QTRQ_U =", value, "QTRQ_U");
            return (Criteria) this;
        }

        public Criteria andQTRQ_UNotEqualTo(Date value) {
            addCriterionForJDBCDate("QTRQ_U <>", value, "QTRQ_U");
            return (Criteria) this;
        }

        public Criteria andQTRQ_UGreaterThan(Date value) {
            addCriterionForJDBCDate("QTRQ_U >", value, "QTRQ_U");
            return (Criteria) this;
        }

        public Criteria andQTRQ_UGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("QTRQ_U >=", value, "QTRQ_U");
            return (Criteria) this;
        }

        public Criteria andQTRQ_ULessThan(Date value) {
            addCriterionForJDBCDate("QTRQ_U <", value, "QTRQ_U");
            return (Criteria) this;
        }

        public Criteria andQTRQ_ULessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("QTRQ_U <=", value, "QTRQ_U");
            return (Criteria) this;
        }

        public Criteria andQTRQ_UIn(List<Date> values) {
            addCriterionForJDBCDate("QTRQ_U in", values, "QTRQ_U");
            return (Criteria) this;
        }

        public Criteria andQTRQ_UNotIn(List<Date> values) {
            addCriterionForJDBCDate("QTRQ_U not in", values, "QTRQ_U");
            return (Criteria) this;
        }

        public Criteria andQTRQ_UBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("QTRQ_U between", value1, value2, "QTRQ_U");
            return (Criteria) this;
        }

        public Criteria andQTRQ_UNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("QTRQ_U not between", value1, value2, "QTRQ_U");
            return (Criteria) this;
        }

        public Criteria andQTLS_UIsNull() {
            addCriterion("QTLS_U is null");
            return (Criteria) this;
        }

        public Criteria andQTLS_UIsNotNull() {
            addCriterion("QTLS_U is not null");
            return (Criteria) this;
        }

        public Criteria andQTLS_UEqualTo(String value) {
            addCriterion("QTLS_U =", value, "QTLS_U");
            return (Criteria) this;
        }

        public Criteria andQTLS_UNotEqualTo(String value) {
            addCriterion("QTLS_U <>", value, "QTLS_U");
            return (Criteria) this;
        }

        public Criteria andQTLS_UGreaterThan(String value) {
            addCriterion("QTLS_U >", value, "QTLS_U");
            return (Criteria) this;
        }

        public Criteria andQTLS_UGreaterThanOrEqualTo(String value) {
            addCriterion("QTLS_U >=", value, "QTLS_U");
            return (Criteria) this;
        }

        public Criteria andQTLS_ULessThan(String value) {
            addCriterion("QTLS_U <", value, "QTLS_U");
            return (Criteria) this;
        }

        public Criteria andQTLS_ULessThanOrEqualTo(String value) {
            addCriterion("QTLS_U <=", value, "QTLS_U");
            return (Criteria) this;
        }

        public Criteria andQTLS_ULike(String value) {
            addCriterion("QTLS_U like", value, "QTLS_U");
            return (Criteria) this;
        }

        public Criteria andQTLS_UNotLike(String value) {
            addCriterion("QTLS_U not like", value, "QTLS_U");
            return (Criteria) this;
        }

        public Criteria andQTLS_UIn(List<String> values) {
            addCriterion("QTLS_U in", values, "QTLS_U");
            return (Criteria) this;
        }

        public Criteria andQTLS_UNotIn(List<String> values) {
            addCriterion("QTLS_U not in", values, "QTLS_U");
            return (Criteria) this;
        }

        public Criteria andQTLS_UBetween(String value1, String value2) {
            addCriterion("QTLS_U between", value1, value2, "QTLS_U");
            return (Criteria) this;
        }

        public Criteria andQTLS_UNotBetween(String value1, String value2) {
            addCriterion("QTLS_U not between", value1, value2, "QTLS_U");
            return (Criteria) this;
        }

        public Criteria andQTJYM_IsNull() {
            addCriterion("QTJYM_ is null");
            return (Criteria) this;
        }

        public Criteria andQTJYM_IsNotNull() {
            addCriterion("QTJYM_ is not null");
            return (Criteria) this;
        }

        public Criteria andQTJYM_EqualTo(String value) {
            addCriterion("QTJYM_ =", value, "QTJYM_");
            return (Criteria) this;
        }

        public Criteria andQTJYM_NotEqualTo(String value) {
            addCriterion("QTJYM_ <>", value, "QTJYM_");
            return (Criteria) this;
        }

        public Criteria andQTJYM_GreaterThan(String value) {
            addCriterion("QTJYM_ >", value, "QTJYM_");
            return (Criteria) this;
        }

        public Criteria andQTJYM_GreaterThanOrEqualTo(String value) {
            addCriterion("QTJYM_ >=", value, "QTJYM_");
            return (Criteria) this;
        }

        public Criteria andQTJYM_LessThan(String value) {
            addCriterion("QTJYM_ <", value, "QTJYM_");
            return (Criteria) this;
        }

        public Criteria andQTJYM_LessThanOrEqualTo(String value) {
            addCriterion("QTJYM_ <=", value, "QTJYM_");
            return (Criteria) this;
        }

        public Criteria andQTJYM_Like(String value) {
            addCriterion("QTJYM_ like", value, "QTJYM_");
            return (Criteria) this;
        }

        public Criteria andQTJYM_NotLike(String value) {
            addCriterion("QTJYM_ not like", value, "QTJYM_");
            return (Criteria) this;
        }

        public Criteria andQTJYM_In(List<String> values) {
            addCriterion("QTJYM_ in", values, "QTJYM_");
            return (Criteria) this;
        }

        public Criteria andQTJYM_NotIn(List<String> values) {
            addCriterion("QTJYM_ not in", values, "QTJYM_");
            return (Criteria) this;
        }

        public Criteria andQTJYM_Between(String value1, String value2) {
            addCriterion("QTJYM_ between", value1, value2, "QTJYM_");
            return (Criteria) this;
        }

        public Criteria andQTJYM_NotBetween(String value1, String value2) {
            addCriterion("QTJYM_ not between", value1, value2, "QTJYM_");
            return (Criteria) this;
        }

        public Criteria andJYZT_UIsNull() {
            addCriterion("JYZT_U is null");
            return (Criteria) this;
        }

        public Criteria andJYZT_UIsNotNull() {
            addCriterion("JYZT_U is not null");
            return (Criteria) this;
        }

        public Criteria andJYZT_UEqualTo(String value) {
            addCriterion("JYZT_U =", value, "JYZT_U");
            return (Criteria) this;
        }

        public Criteria andJYZT_UNotEqualTo(String value) {
            addCriterion("JYZT_U <>", value, "JYZT_U");
            return (Criteria) this;
        }

        public Criteria andJYZT_UGreaterThan(String value) {
            addCriterion("JYZT_U >", value, "JYZT_U");
            return (Criteria) this;
        }

        public Criteria andJYZT_UGreaterThanOrEqualTo(String value) {
            addCriterion("JYZT_U >=", value, "JYZT_U");
            return (Criteria) this;
        }

        public Criteria andJYZT_ULessThan(String value) {
            addCriterion("JYZT_U <", value, "JYZT_U");
            return (Criteria) this;
        }

        public Criteria andJYZT_ULessThanOrEqualTo(String value) {
            addCriterion("JYZT_U <=", value, "JYZT_U");
            return (Criteria) this;
        }

        public Criteria andJYZT_ULike(String value) {
            addCriterion("JYZT_U like", value, "JYZT_U");
            return (Criteria) this;
        }

        public Criteria andJYZT_UNotLike(String value) {
            addCriterion("JYZT_U not like", value, "JYZT_U");
            return (Criteria) this;
        }

        public Criteria andJYZT_UIn(List<String> values) {
            addCriterion("JYZT_U in", values, "JYZT_U");
            return (Criteria) this;
        }

        public Criteria andJYZT_UNotIn(List<String> values) {
            addCriterion("JYZT_U not in", values, "JYZT_U");
            return (Criteria) this;
        }

        public Criteria andJYZT_UBetween(String value1, String value2) {
            addCriterion("JYZT_U between", value1, value2, "JYZT_U");
            return (Criteria) this;
        }

        public Criteria andJYZT_UNotBetween(String value1, String value2) {
            addCriterion("JYZT_U not between", value1, value2, "JYZT_U");
            return (Criteria) this;
        }

        public Criteria andSPXX_UIsNull() {
            addCriterion("SPXX_U is null");
            return (Criteria) this;
        }

        public Criteria andSPXX_UIsNotNull() {
            addCriterion("SPXX_U is not null");
            return (Criteria) this;
        }

        public Criteria andSPXX_UEqualTo(String value) {
            addCriterion("SPXX_U =", value, "SPXX_U");
            return (Criteria) this;
        }

        public Criteria andSPXX_UNotEqualTo(String value) {
            addCriterion("SPXX_U <>", value, "SPXX_U");
            return (Criteria) this;
        }

        public Criteria andSPXX_UGreaterThan(String value) {
            addCriterion("SPXX_U >", value, "SPXX_U");
            return (Criteria) this;
        }

        public Criteria andSPXX_UGreaterThanOrEqualTo(String value) {
            addCriterion("SPXX_U >=", value, "SPXX_U");
            return (Criteria) this;
        }

        public Criteria andSPXX_ULessThan(String value) {
            addCriterion("SPXX_U <", value, "SPXX_U");
            return (Criteria) this;
        }

        public Criteria andSPXX_ULessThanOrEqualTo(String value) {
            addCriterion("SPXX_U <=", value, "SPXX_U");
            return (Criteria) this;
        }

        public Criteria andSPXX_ULike(String value) {
            addCriterion("SPXX_U like", value, "SPXX_U");
            return (Criteria) this;
        }

        public Criteria andSPXX_UNotLike(String value) {
            addCriterion("SPXX_U not like", value, "SPXX_U");
            return (Criteria) this;
        }

        public Criteria andSPXX_UIn(List<String> values) {
            addCriterion("SPXX_U in", values, "SPXX_U");
            return (Criteria) this;
        }

        public Criteria andSPXX_UNotIn(List<String> values) {
            addCriterion("SPXX_U not in", values, "SPXX_U");
            return (Criteria) this;
        }

        public Criteria andSPXX_UBetween(String value1, String value2) {
            addCriterion("SPXX_U between", value1, value2, "SPXX_U");
            return (Criteria) this;
        }

        public Criteria andSPXX_UNotBetween(String value1, String value2) {
            addCriterion("SPXX_U not between", value1, value2, "SPXX_U");
            return (Criteria) this;
        }

        public Criteria andFKM_UUIsNull() {
            addCriterion("FKM_UU is null");
            return (Criteria) this;
        }

        public Criteria andFKM_UUIsNotNull() {
            addCriterion("FKM_UU is not null");
            return (Criteria) this;
        }

        public Criteria andFKM_UUEqualTo(String value) {
            addCriterion("FKM_UU =", value, "FKM_UU");
            return (Criteria) this;
        }

        public Criteria andFKM_UUNotEqualTo(String value) {
            addCriterion("FKM_UU <>", value, "FKM_UU");
            return (Criteria) this;
        }

        public Criteria andFKM_UUGreaterThan(String value) {
            addCriterion("FKM_UU >", value, "FKM_UU");
            return (Criteria) this;
        }

        public Criteria andFKM_UUGreaterThanOrEqualTo(String value) {
            addCriterion("FKM_UU >=", value, "FKM_UU");
            return (Criteria) this;
        }

        public Criteria andFKM_UULessThan(String value) {
            addCriterion("FKM_UU <", value, "FKM_UU");
            return (Criteria) this;
        }

        public Criteria andFKM_UULessThanOrEqualTo(String value) {
            addCriterion("FKM_UU <=", value, "FKM_UU");
            return (Criteria) this;
        }

        public Criteria andFKM_UULike(String value) {
            addCriterion("FKM_UU like", value, "FKM_UU");
            return (Criteria) this;
        }

        public Criteria andFKM_UUNotLike(String value) {
            addCriterion("FKM_UU not like", value, "FKM_UU");
            return (Criteria) this;
        }

        public Criteria andFKM_UUIn(List<String> values) {
            addCriterion("FKM_UU in", values, "FKM_UU");
            return (Criteria) this;
        }

        public Criteria andFKM_UUNotIn(List<String> values) {
            addCriterion("FKM_UU not in", values, "FKM_UU");
            return (Criteria) this;
        }

        public Criteria andFKM_UUBetween(String value1, String value2) {
            addCriterion("FKM_UU between", value1, value2, "FKM_UU");
            return (Criteria) this;
        }

        public Criteria andFKM_UUNotBetween(String value1, String value2) {
            addCriterion("FKM_UU not between", value1, value2, "FKM_UU");
            return (Criteria) this;
        }

        public Criteria andWHRQ_UIsNull() {
            addCriterion("WHRQ_U is null");
            return (Criteria) this;
        }

        public Criteria andWHRQ_UIsNotNull() {
            addCriterion("WHRQ_U is not null");
            return (Criteria) this;
        }

        public Criteria andWHRQ_UEqualTo(Date value) {
            addCriterionForJDBCDate("WHRQ_U =", value, "WHRQ_U");
            return (Criteria) this;
        }

        public Criteria andWHRQ_UNotEqualTo(Date value) {
            addCriterionForJDBCDate("WHRQ_U <>", value, "WHRQ_U");
            return (Criteria) this;
        }

        public Criteria andWHRQ_UGreaterThan(Date value) {
            addCriterionForJDBCDate("WHRQ_U >", value, "WHRQ_U");
            return (Criteria) this;
        }

        public Criteria andWHRQ_UGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("WHRQ_U >=", value, "WHRQ_U");
            return (Criteria) this;
        }

        public Criteria andWHRQ_ULessThan(Date value) {
            addCriterionForJDBCDate("WHRQ_U <", value, "WHRQ_U");
            return (Criteria) this;
        }

        public Criteria andWHRQ_ULessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("WHRQ_U <=", value, "WHRQ_U");
            return (Criteria) this;
        }

        public Criteria andWHRQ_UIn(List<Date> values) {
            addCriterionForJDBCDate("WHRQ_U in", values, "WHRQ_U");
            return (Criteria) this;
        }

        public Criteria andWHRQ_UNotIn(List<Date> values) {
            addCriterionForJDBCDate("WHRQ_U not in", values, "WHRQ_U");
            return (Criteria) this;
        }

        public Criteria andWHRQ_UBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("WHRQ_U between", value1, value2, "WHRQ_U");
            return (Criteria) this;
        }

        public Criteria andWHRQ_UNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("WHRQ_U not between", value1, value2, "WHRQ_U");
            return (Criteria) this;
        }

        public Criteria andWHSJ_UIsNull() {
            addCriterion("WHSJ_U is null");
            return (Criteria) this;
        }

        public Criteria andWHSJ_UIsNotNull() {
            addCriterion("WHSJ_U is not null");
            return (Criteria) this;
        }

        public Criteria andWHSJ_UEqualTo(Date value) {
            addCriterionForJDBCTime("WHSJ_U =", value, "WHSJ_U");
            return (Criteria) this;
        }

        public Criteria andWHSJ_UNotEqualTo(Date value) {
            addCriterionForJDBCTime("WHSJ_U <>", value, "WHSJ_U");
            return (Criteria) this;
        }

        public Criteria andWHSJ_UGreaterThan(Date value) {
            addCriterionForJDBCTime("WHSJ_U >", value, "WHSJ_U");
            return (Criteria) this;
        }

        public Criteria andWHSJ_UGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("WHSJ_U >=", value, "WHSJ_U");
            return (Criteria) this;
        }

        public Criteria andWHSJ_ULessThan(Date value) {
            addCriterionForJDBCTime("WHSJ_U <", value, "WHSJ_U");
            return (Criteria) this;
        }

        public Criteria andWHSJ_ULessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("WHSJ_U <=", value, "WHSJ_U");
            return (Criteria) this;
        }

        public Criteria andWHSJ_UIn(List<Date> values) {
            addCriterionForJDBCTime("WHSJ_U in", values, "WHSJ_U");
            return (Criteria) this;
        }

        public Criteria andWHSJ_UNotIn(List<Date> values) {
            addCriterionForJDBCTime("WHSJ_U not in", values, "WHSJ_U");
            return (Criteria) this;
        }

        public Criteria andWHSJ_UBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("WHSJ_U between", value1, value2, "WHSJ_U");
            return (Criteria) this;
        }

        public Criteria andWHSJ_UNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("WHSJ_U not between", value1, value2, "WHSJ_U");
            return (Criteria) this;
        }

        public Criteria andWHR_UUIsNull() {
            addCriterion("WHR_UU is null");
            return (Criteria) this;
        }

        public Criteria andWHR_UUIsNotNull() {
            addCriterion("WHR_UU is not null");
            return (Criteria) this;
        }

        public Criteria andWHR_UUEqualTo(String value) {
            addCriterion("WHR_UU =", value, "WHR_UU");
            return (Criteria) this;
        }

        public Criteria andWHR_UUNotEqualTo(String value) {
            addCriterion("WHR_UU <>", value, "WHR_UU");
            return (Criteria) this;
        }

        public Criteria andWHR_UUGreaterThan(String value) {
            addCriterion("WHR_UU >", value, "WHR_UU");
            return (Criteria) this;
        }

        public Criteria andWHR_UUGreaterThanOrEqualTo(String value) {
            addCriterion("WHR_UU >=", value, "WHR_UU");
            return (Criteria) this;
        }

        public Criteria andWHR_UULessThan(String value) {
            addCriterion("WHR_UU <", value, "WHR_UU");
            return (Criteria) this;
        }

        public Criteria andWHR_UULessThanOrEqualTo(String value) {
            addCriterion("WHR_UU <=", value, "WHR_UU");
            return (Criteria) this;
        }

        public Criteria andWHR_UULike(String value) {
            addCriterion("WHR_UU like", value, "WHR_UU");
            return (Criteria) this;
        }

        public Criteria andWHR_UUNotLike(String value) {
            addCriterion("WHR_UU not like", value, "WHR_UU");
            return (Criteria) this;
        }

        public Criteria andWHR_UUIn(List<String> values) {
            addCriterion("WHR_UU in", values, "WHR_UU");
            return (Criteria) this;
        }

        public Criteria andWHR_UUNotIn(List<String> values) {
            addCriterion("WHR_UU not in", values, "WHR_UU");
            return (Criteria) this;
        }

        public Criteria andWHR_UUBetween(String value1, String value2) {
            addCriterion("WHR_UU between", value1, value2, "WHR_UU");
            return (Criteria) this;
        }

        public Criteria andWHR_UUNotBetween(String value1, String value2) {
            addCriterion("WHR_UU not between", value1, value2, "WHR_UU");
            return (Criteria) this;
        }

        public Criteria andSJC_UUIsNull() {
            addCriterion("SJC_UU is null");
            return (Criteria) this;
        }

        public Criteria andSJC_UUIsNotNull() {
            addCriterion("SJC_UU is not null");
            return (Criteria) this;
        }

        public Criteria andSJC_UUEqualTo(Date value) {
            addCriterion("SJC_UU =", value, "SJC_UU");
            return (Criteria) this;
        }

        public Criteria andSJC_UUNotEqualTo(Date value) {
            addCriterion("SJC_UU <>", value, "SJC_UU");
            return (Criteria) this;
        }

        public Criteria andSJC_UUGreaterThan(Date value) {
            addCriterion("SJC_UU >", value, "SJC_UU");
            return (Criteria) this;
        }

        public Criteria andSJC_UUGreaterThanOrEqualTo(Date value) {
            addCriterion("SJC_UU >=", value, "SJC_UU");
            return (Criteria) this;
        }

        public Criteria andSJC_UULessThan(Date value) {
            addCriterion("SJC_UU <", value, "SJC_UU");
            return (Criteria) this;
        }

        public Criteria andSJC_UULessThanOrEqualTo(Date value) {
            addCriterion("SJC_UU <=", value, "SJC_UU");
            return (Criteria) this;
        }

        public Criteria andSJC_UUIn(List<Date> values) {
            addCriterion("SJC_UU in", values, "SJC_UU");
            return (Criteria) this;
        }

        public Criteria andSJC_UUNotIn(List<Date> values) {
            addCriterion("SJC_UU not in", values, "SJC_UU");
            return (Criteria) this;
        }

        public Criteria andSJC_UUBetween(Date value1, Date value2) {
            addCriterion("SJC_UU between", value1, value2, "SJC_UU");
            return (Criteria) this;
        }

        public Criteria andSJC_UUNotBetween(Date value1, Date value2) {
            addCriterion("SJC_UU not between", value1, value2, "SJC_UU");
            return (Criteria) this;
        }

        public Criteria andJLZT_UIsNull() {
            addCriterion("JLZT_U is null");
            return (Criteria) this;
        }

        public Criteria andJLZT_UIsNotNull() {
            addCriterion("JLZT_U is not null");
            return (Criteria) this;
        }

        public Criteria andJLZT_UEqualTo(String value) {
            addCriterion("JLZT_U =", value, "JLZT_U");
            return (Criteria) this;
        }

        public Criteria andJLZT_UNotEqualTo(String value) {
            addCriterion("JLZT_U <>", value, "JLZT_U");
            return (Criteria) this;
        }

        public Criteria andJLZT_UGreaterThan(String value) {
            addCriterion("JLZT_U >", value, "JLZT_U");
            return (Criteria) this;
        }

        public Criteria andJLZT_UGreaterThanOrEqualTo(String value) {
            addCriterion("JLZT_U >=", value, "JLZT_U");
            return (Criteria) this;
        }

        public Criteria andJLZT_ULessThan(String value) {
            addCriterion("JLZT_U <", value, "JLZT_U");
            return (Criteria) this;
        }

        public Criteria andJLZT_ULessThanOrEqualTo(String value) {
            addCriterion("JLZT_U <=", value, "JLZT_U");
            return (Criteria) this;
        }

        public Criteria andJLZT_ULike(String value) {
            addCriterion("JLZT_U like", value, "JLZT_U");
            return (Criteria) this;
        }

        public Criteria andJLZT_UNotLike(String value) {
            addCriterion("JLZT_U not like", value, "JLZT_U");
            return (Criteria) this;
        }

        public Criteria andJLZT_UIn(List<String> values) {
            addCriterion("JLZT_U in", values, "JLZT_U");
            return (Criteria) this;
        }

        public Criteria andJLZT_UNotIn(List<String> values) {
            addCriterion("JLZT_U not in", values, "JLZT_U");
            return (Criteria) this;
        }

        public Criteria andJLZT_UBetween(String value1, String value2) {
            addCriterion("JLZT_U between", value1, value2, "JLZT_U");
            return (Criteria) this;
        }

        public Criteria andJLZT_UNotBetween(String value1, String value2) {
            addCriterion("JLZT_U not between", value1, value2, "JLZT_U");
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