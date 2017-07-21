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

        public Criteria andDSFRQ_IsNull() {
            addCriterion("DSFRQ_ is null");
            return (Criteria) this;
        }

        public Criteria andDSFRQ_IsNotNull() {
            addCriterion("DSFRQ_ is not null");
            return (Criteria) this;
        }

        public Criteria andDSFRQ_EqualTo(Date value) {
            addCriterionForJDBCDate("DSFRQ_ =", value, "DSFRQ_");
            return (Criteria) this;
        }

        public Criteria andDSFRQ_NotEqualTo(Date value) {
            addCriterionForJDBCDate("DSFRQ_ <>", value, "DSFRQ_");
            return (Criteria) this;
        }

        public Criteria andDSFRQ_GreaterThan(Date value) {
            addCriterionForJDBCDate("DSFRQ_ >", value, "DSFRQ_");
            return (Criteria) this;
        }

        public Criteria andDSFRQ_GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DSFRQ_ >=", value, "DSFRQ_");
            return (Criteria) this;
        }

        public Criteria andDSFRQ_LessThan(Date value) {
            addCriterionForJDBCDate("DSFRQ_ <", value, "DSFRQ_");
            return (Criteria) this;
        }

        public Criteria andDSFRQ_LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DSFRQ_ <=", value, "DSFRQ_");
            return (Criteria) this;
        }

        public Criteria andDSFRQ_In(List<Date> values) {
            addCriterionForJDBCDate("DSFRQ_ in", values, "DSFRQ_");
            return (Criteria) this;
        }

        public Criteria andDSFRQ_NotIn(List<Date> values) {
            addCriterionForJDBCDate("DSFRQ_ not in", values, "DSFRQ_");
            return (Criteria) this;
        }

        public Criteria andDSFRQ_Between(Date value1, Date value2) {
            addCriterionForJDBCDate("DSFRQ_ between", value1, value2, "DSFRQ_");
            return (Criteria) this;
        }

        public Criteria andDSFRQ_NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DSFRQ_ not between", value1, value2, "DSFRQ_");
            return (Criteria) this;
        }

        public Criteria andDSFLS_IsNull() {
            addCriterion("DSFLS_ is null");
            return (Criteria) this;
        }

        public Criteria andDSFLS_IsNotNull() {
            addCriterion("DSFLS_ is not null");
            return (Criteria) this;
        }

        public Criteria andDSFLS_EqualTo(String value) {
            addCriterion("DSFLS_ =", value, "DSFLS_");
            return (Criteria) this;
        }

        public Criteria andDSFLS_NotEqualTo(String value) {
            addCriterion("DSFLS_ <>", value, "DSFLS_");
            return (Criteria) this;
        }

        public Criteria andDSFLS_GreaterThan(String value) {
            addCriterion("DSFLS_ >", value, "DSFLS_");
            return (Criteria) this;
        }

        public Criteria andDSFLS_GreaterThanOrEqualTo(String value) {
            addCriterion("DSFLS_ >=", value, "DSFLS_");
            return (Criteria) this;
        }

        public Criteria andDSFLS_LessThan(String value) {
            addCriterion("DSFLS_ <", value, "DSFLS_");
            return (Criteria) this;
        }

        public Criteria andDSFLS_LessThanOrEqualTo(String value) {
            addCriterion("DSFLS_ <=", value, "DSFLS_");
            return (Criteria) this;
        }

        public Criteria andDSFLS_Like(String value) {
            addCriterion("DSFLS_ like", value, "DSFLS_");
            return (Criteria) this;
        }

        public Criteria andDSFLS_NotLike(String value) {
            addCriterion("DSFLS_ not like", value, "DSFLS_");
            return (Criteria) this;
        }

        public Criteria andDSFLS_In(List<String> values) {
            addCriterion("DSFLS_ in", values, "DSFLS_");
            return (Criteria) this;
        }

        public Criteria andDSFLS_NotIn(List<String> values) {
            addCriterion("DSFLS_ not in", values, "DSFLS_");
            return (Criteria) this;
        }

        public Criteria andDSFLS_Between(String value1, String value2) {
            addCriterion("DSFLS_ between", value1, value2, "DSFLS_");
            return (Criteria) this;
        }

        public Criteria andDSFLS_NotBetween(String value1, String value2) {
            addCriterion("DSFLS_ not between", value1, value2, "DSFLS_");
            return (Criteria) this;
        }

        public Criteria andAPIMC_IsNull() {
            addCriterion("APIMC_ is null");
            return (Criteria) this;
        }

        public Criteria andAPIMC_IsNotNull() {
            addCriterion("APIMC_ is not null");
            return (Criteria) this;
        }

        public Criteria andAPIMC_EqualTo(String value) {
            addCriterion("APIMC_ =", value, "APIMC_");
            return (Criteria) this;
        }

        public Criteria andAPIMC_NotEqualTo(String value) {
            addCriterion("APIMC_ <>", value, "APIMC_");
            return (Criteria) this;
        }

        public Criteria andAPIMC_GreaterThan(String value) {
            addCriterion("APIMC_ >", value, "APIMC_");
            return (Criteria) this;
        }

        public Criteria andAPIMC_GreaterThanOrEqualTo(String value) {
            addCriterion("APIMC_ >=", value, "APIMC_");
            return (Criteria) this;
        }

        public Criteria andAPIMC_LessThan(String value) {
            addCriterion("APIMC_ <", value, "APIMC_");
            return (Criteria) this;
        }

        public Criteria andAPIMC_LessThanOrEqualTo(String value) {
            addCriterion("APIMC_ <=", value, "APIMC_");
            return (Criteria) this;
        }

        public Criteria andAPIMC_Like(String value) {
            addCriterion("APIMC_ like", value, "APIMC_");
            return (Criteria) this;
        }

        public Criteria andAPIMC_NotLike(String value) {
            addCriterion("APIMC_ not like", value, "APIMC_");
            return (Criteria) this;
        }

        public Criteria andAPIMC_In(List<String> values) {
            addCriterion("APIMC_ in", values, "APIMC_");
            return (Criteria) this;
        }

        public Criteria andAPIMC_NotIn(List<String> values) {
            addCriterion("APIMC_ not in", values, "APIMC_");
            return (Criteria) this;
        }

        public Criteria andAPIMC_Between(String value1, String value2) {
            addCriterion("APIMC_ between", value1, value2, "APIMC_");
            return (Criteria) this;
        }

        public Criteria andAPIMC_NotBetween(String value1, String value2) {
            addCriterion("APIMC_ not between", value1, value2, "APIMC_");
            return (Criteria) this;
        }

        public Criteria andCWDM_UIsNull() {
            addCriterion("CWDM_U is null");
            return (Criteria) this;
        }

        public Criteria andCWDM_UIsNotNull() {
            addCriterion("CWDM_U is not null");
            return (Criteria) this;
        }

        public Criteria andCWDM_UEqualTo(String value) {
            addCriterion("CWDM_U =", value, "CWDM_U");
            return (Criteria) this;
        }

        public Criteria andCWDM_UNotEqualTo(String value) {
            addCriterion("CWDM_U <>", value, "CWDM_U");
            return (Criteria) this;
        }

        public Criteria andCWDM_UGreaterThan(String value) {
            addCriterion("CWDM_U >", value, "CWDM_U");
            return (Criteria) this;
        }

        public Criteria andCWDM_UGreaterThanOrEqualTo(String value) {
            addCriterion("CWDM_U >=", value, "CWDM_U");
            return (Criteria) this;
        }

        public Criteria andCWDM_ULessThan(String value) {
            addCriterion("CWDM_U <", value, "CWDM_U");
            return (Criteria) this;
        }

        public Criteria andCWDM_ULessThanOrEqualTo(String value) {
            addCriterion("CWDM_U <=", value, "CWDM_U");
            return (Criteria) this;
        }

        public Criteria andCWDM_ULike(String value) {
            addCriterion("CWDM_U like", value, "CWDM_U");
            return (Criteria) this;
        }

        public Criteria andCWDM_UNotLike(String value) {
            addCriterion("CWDM_U not like", value, "CWDM_U");
            return (Criteria) this;
        }

        public Criteria andCWDM_UIn(List<String> values) {
            addCriterion("CWDM_U in", values, "CWDM_U");
            return (Criteria) this;
        }

        public Criteria andCWDM_UNotIn(List<String> values) {
            addCriterion("CWDM_U not in", values, "CWDM_U");
            return (Criteria) this;
        }

        public Criteria andCWDM_UBetween(String value1, String value2) {
            addCriterion("CWDM_U between", value1, value2, "CWDM_U");
            return (Criteria) this;
        }

        public Criteria andCWDM_UNotBetween(String value1, String value2) {
            addCriterion("CWDM_U not between", value1, value2, "CWDM_U");
            return (Criteria) this;
        }

        public Criteria andCWXX_UIsNull() {
            addCriterion("CWXX_U is null");
            return (Criteria) this;
        }

        public Criteria andCWXX_UIsNotNull() {
            addCriterion("CWXX_U is not null");
            return (Criteria) this;
        }

        public Criteria andCWXX_UEqualTo(String value) {
            addCriterion("CWXX_U =", value, "CWXX_U");
            return (Criteria) this;
        }

        public Criteria andCWXX_UNotEqualTo(String value) {
            addCriterion("CWXX_U <>", value, "CWXX_U");
            return (Criteria) this;
        }

        public Criteria andCWXX_UGreaterThan(String value) {
            addCriterion("CWXX_U >", value, "CWXX_U");
            return (Criteria) this;
        }

        public Criteria andCWXX_UGreaterThanOrEqualTo(String value) {
            addCriterion("CWXX_U >=", value, "CWXX_U");
            return (Criteria) this;
        }

        public Criteria andCWXX_ULessThan(String value) {
            addCriterion("CWXX_U <", value, "CWXX_U");
            return (Criteria) this;
        }

        public Criteria andCWXX_ULessThanOrEqualTo(String value) {
            addCriterion("CWXX_U <=", value, "CWXX_U");
            return (Criteria) this;
        }

        public Criteria andCWXX_ULike(String value) {
            addCriterion("CWXX_U like", value, "CWXX_U");
            return (Criteria) this;
        }

        public Criteria andCWXX_UNotLike(String value) {
            addCriterion("CWXX_U not like", value, "CWXX_U");
            return (Criteria) this;
        }

        public Criteria andCWXX_UIn(List<String> values) {
            addCriterion("CWXX_U in", values, "CWXX_U");
            return (Criteria) this;
        }

        public Criteria andCWXX_UNotIn(List<String> values) {
            addCriterion("CWXX_U not in", values, "CWXX_U");
            return (Criteria) this;
        }

        public Criteria andCWXX_UBetween(String value1, String value2) {
            addCriterion("CWXX_U between", value1, value2, "CWXX_U");
            return (Criteria) this;
        }

        public Criteria andCWXX_UNotBetween(String value1, String value2) {
            addCriterion("CWXX_U not between", value1, value2, "CWXX_U");
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

        public Criteria andIP_UUUIsNull() {
            addCriterion("IP_UUU is null");
            return (Criteria) this;
        }

        public Criteria andIP_UUUIsNotNull() {
            addCriterion("IP_UUU is not null");
            return (Criteria) this;
        }

        public Criteria andIP_UUUEqualTo(String value) {
            addCriterion("IP_UUU =", value, "IP_UUU");
            return (Criteria) this;
        }

        public Criteria andIP_UUUNotEqualTo(String value) {
            addCriterion("IP_UUU <>", value, "IP_UUU");
            return (Criteria) this;
        }

        public Criteria andIP_UUUGreaterThan(String value) {
            addCriterion("IP_UUU >", value, "IP_UUU");
            return (Criteria) this;
        }

        public Criteria andIP_UUUGreaterThanOrEqualTo(String value) {
            addCriterion("IP_UUU >=", value, "IP_UUU");
            return (Criteria) this;
        }

        public Criteria andIP_UUULessThan(String value) {
            addCriterion("IP_UUU <", value, "IP_UUU");
            return (Criteria) this;
        }

        public Criteria andIP_UUULessThanOrEqualTo(String value) {
            addCriterion("IP_UUU <=", value, "IP_UUU");
            return (Criteria) this;
        }

        public Criteria andIP_UUULike(String value) {
            addCriterion("IP_UUU like", value, "IP_UUU");
            return (Criteria) this;
        }

        public Criteria andIP_UUUNotLike(String value) {
            addCriterion("IP_UUU not like", value, "IP_UUU");
            return (Criteria) this;
        }

        public Criteria andIP_UUUIn(List<String> values) {
            addCriterion("IP_UUU in", values, "IP_UUU");
            return (Criteria) this;
        }

        public Criteria andIP_UUUNotIn(List<String> values) {
            addCriterion("IP_UUU not in", values, "IP_UUU");
            return (Criteria) this;
        }

        public Criteria andIP_UUUBetween(String value1, String value2) {
            addCriterion("IP_UUU between", value1, value2, "IP_UUU");
            return (Criteria) this;
        }

        public Criteria andIP_UUUNotBetween(String value1, String value2) {
            addCriterion("IP_UUU not between", value1, value2, "IP_UUU");
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