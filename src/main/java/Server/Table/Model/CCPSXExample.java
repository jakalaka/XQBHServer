package Server.Table.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CCPSXExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CCPSXExample() {
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

        public Criteria andCPBH_UIsNull() {
            addCriterion("CPBH_U is null");
            return (Criteria) this;
        }

        public Criteria andCPBH_UIsNotNull() {
            addCriterion("CPBH_U is not null");
            return (Criteria) this;
        }

        public Criteria andCPBH_UEqualTo(String value) {
            addCriterion("CPBH_U =", value, "CPBH_U");
            return (Criteria) this;
        }

        public Criteria andCPBH_UNotEqualTo(String value) {
            addCriterion("CPBH_U <>", value, "CPBH_U");
            return (Criteria) this;
        }

        public Criteria andCPBH_UGreaterThan(String value) {
            addCriterion("CPBH_U >", value, "CPBH_U");
            return (Criteria) this;
        }

        public Criteria andCPBH_UGreaterThanOrEqualTo(String value) {
            addCriterion("CPBH_U >=", value, "CPBH_U");
            return (Criteria) this;
        }

        public Criteria andCPBH_ULessThan(String value) {
            addCriterion("CPBH_U <", value, "CPBH_U");
            return (Criteria) this;
        }

        public Criteria andCPBH_ULessThanOrEqualTo(String value) {
            addCriterion("CPBH_U <=", value, "CPBH_U");
            return (Criteria) this;
        }

        public Criteria andCPBH_ULike(String value) {
            addCriterion("CPBH_U like", value, "CPBH_U");
            return (Criteria) this;
        }

        public Criteria andCPBH_UNotLike(String value) {
            addCriterion("CPBH_U not like", value, "CPBH_U");
            return (Criteria) this;
        }

        public Criteria andCPBH_UIn(List<String> values) {
            addCriterion("CPBH_U in", values, "CPBH_U");
            return (Criteria) this;
        }

        public Criteria andCPBH_UNotIn(List<String> values) {
            addCriterion("CPBH_U not in", values, "CPBH_U");
            return (Criteria) this;
        }

        public Criteria andCPBH_UBetween(String value1, String value2) {
            addCriterion("CPBH_U between", value1, value2, "CPBH_U");
            return (Criteria) this;
        }

        public Criteria andCPBH_UNotBetween(String value1, String value2) {
            addCriterion("CPBH_U not between", value1, value2, "CPBH_U");
            return (Criteria) this;
        }

        public Criteria andCPMC_UIsNull() {
            addCriterion("CPMC_U is null");
            return (Criteria) this;
        }

        public Criteria andCPMC_UIsNotNull() {
            addCriterion("CPMC_U is not null");
            return (Criteria) this;
        }

        public Criteria andCPMC_UEqualTo(String value) {
            addCriterion("CPMC_U =", value, "CPMC_U");
            return (Criteria) this;
        }

        public Criteria andCPMC_UNotEqualTo(String value) {
            addCriterion("CPMC_U <>", value, "CPMC_U");
            return (Criteria) this;
        }

        public Criteria andCPMC_UGreaterThan(String value) {
            addCriterion("CPMC_U >", value, "CPMC_U");
            return (Criteria) this;
        }

        public Criteria andCPMC_UGreaterThanOrEqualTo(String value) {
            addCriterion("CPMC_U >=", value, "CPMC_U");
            return (Criteria) this;
        }

        public Criteria andCPMC_ULessThan(String value) {
            addCriterion("CPMC_U <", value, "CPMC_U");
            return (Criteria) this;
        }

        public Criteria andCPMC_ULessThanOrEqualTo(String value) {
            addCriterion("CPMC_U <=", value, "CPMC_U");
            return (Criteria) this;
        }

        public Criteria andCPMC_ULike(String value) {
            addCriterion("CPMC_U like", value, "CPMC_U");
            return (Criteria) this;
        }

        public Criteria andCPMC_UNotLike(String value) {
            addCriterion("CPMC_U not like", value, "CPMC_U");
            return (Criteria) this;
        }

        public Criteria andCPMC_UIn(List<String> values) {
            addCriterion("CPMC_U in", values, "CPMC_U");
            return (Criteria) this;
        }

        public Criteria andCPMC_UNotIn(List<String> values) {
            addCriterion("CPMC_U not in", values, "CPMC_U");
            return (Criteria) this;
        }

        public Criteria andCPMC_UBetween(String value1, String value2) {
            addCriterion("CPMC_U between", value1, value2, "CPMC_U");
            return (Criteria) this;
        }

        public Criteria andCPMC_UNotBetween(String value1, String value2) {
            addCriterion("CPMC_U not between", value1, value2, "CPMC_U");
            return (Criteria) this;
        }

        public Criteria andJFZQ_UIsNull() {
            addCriterion("JFZQ_U is null");
            return (Criteria) this;
        }

        public Criteria andJFZQ_UIsNotNull() {
            addCriterion("JFZQ_U is not null");
            return (Criteria) this;
        }

        public Criteria andJFZQ_UEqualTo(String value) {
            addCriterion("JFZQ_U =", value, "JFZQ_U");
            return (Criteria) this;
        }

        public Criteria andJFZQ_UNotEqualTo(String value) {
            addCriterion("JFZQ_U <>", value, "JFZQ_U");
            return (Criteria) this;
        }

        public Criteria andJFZQ_UGreaterThan(String value) {
            addCriterion("JFZQ_U >", value, "JFZQ_U");
            return (Criteria) this;
        }

        public Criteria andJFZQ_UGreaterThanOrEqualTo(String value) {
            addCriterion("JFZQ_U >=", value, "JFZQ_U");
            return (Criteria) this;
        }

        public Criteria andJFZQ_ULessThan(String value) {
            addCriterion("JFZQ_U <", value, "JFZQ_U");
            return (Criteria) this;
        }

        public Criteria andJFZQ_ULessThanOrEqualTo(String value) {
            addCriterion("JFZQ_U <=", value, "JFZQ_U");
            return (Criteria) this;
        }

        public Criteria andJFZQ_ULike(String value) {
            addCriterion("JFZQ_U like", value, "JFZQ_U");
            return (Criteria) this;
        }

        public Criteria andJFZQ_UNotLike(String value) {
            addCriterion("JFZQ_U not like", value, "JFZQ_U");
            return (Criteria) this;
        }

        public Criteria andJFZQ_UIn(List<String> values) {
            addCriterion("JFZQ_U in", values, "JFZQ_U");
            return (Criteria) this;
        }

        public Criteria andJFZQ_UNotIn(List<String> values) {
            addCriterion("JFZQ_U not in", values, "JFZQ_U");
            return (Criteria) this;
        }

        public Criteria andJFZQ_UBetween(String value1, String value2) {
            addCriterion("JFZQ_U between", value1, value2, "JFZQ_U");
            return (Criteria) this;
        }

        public Criteria andJFZQ_UNotBetween(String value1, String value2) {
            addCriterion("JFZQ_U not between", value1, value2, "JFZQ_U");
            return (Criteria) this;
        }

        public Criteria andQJJE_UIsNull() {
            addCriterion("QJJE_U is null");
            return (Criteria) this;
        }

        public Criteria andQJJE_UIsNotNull() {
            addCriterion("QJJE_U is not null");
            return (Criteria) this;
        }

        public Criteria andQJJE_UEqualTo(Long value) {
            addCriterion("QJJE_U =", value, "QJJE_U");
            return (Criteria) this;
        }

        public Criteria andQJJE_UNotEqualTo(Long value) {
            addCriterion("QJJE_U <>", value, "QJJE_U");
            return (Criteria) this;
        }

        public Criteria andQJJE_UGreaterThan(Long value) {
            addCriterion("QJJE_U >", value, "QJJE_U");
            return (Criteria) this;
        }

        public Criteria andQJJE_UGreaterThanOrEqualTo(Long value) {
            addCriterion("QJJE_U >=", value, "QJJE_U");
            return (Criteria) this;
        }

        public Criteria andQJJE_ULessThan(Long value) {
            addCriterion("QJJE_U <", value, "QJJE_U");
            return (Criteria) this;
        }

        public Criteria andQJJE_ULessThanOrEqualTo(Long value) {
            addCriterion("QJJE_U <=", value, "QJJE_U");
            return (Criteria) this;
        }

        public Criteria andQJJE_UIn(List<Long> values) {
            addCriterion("QJJE_U in", values, "QJJE_U");
            return (Criteria) this;
        }

        public Criteria andQJJE_UNotIn(List<Long> values) {
            addCriterion("QJJE_U not in", values, "QJJE_U");
            return (Criteria) this;
        }

        public Criteria andQJJE_UBetween(Long value1, Long value2) {
            addCriterion("QJJE_U between", value1, value2, "QJJE_U");
            return (Criteria) this;
        }

        public Criteria andQJJE_UNotBetween(Long value1, Long value2) {
            addCriterion("QJJE_U not between", value1, value2, "QJJE_U");
            return (Criteria) this;
        }

        public Criteria andXZKHJBIsNull() {
            addCriterion("XZKHJB is null");
            return (Criteria) this;
        }

        public Criteria andXZKHJBIsNotNull() {
            addCriterion("XZKHJB is not null");
            return (Criteria) this;
        }

        public Criteria andXZKHJBEqualTo(String value) {
            addCriterion("XZKHJB =", value, "XZKHJB");
            return (Criteria) this;
        }

        public Criteria andXZKHJBNotEqualTo(String value) {
            addCriterion("XZKHJB <>", value, "XZKHJB");
            return (Criteria) this;
        }

        public Criteria andXZKHJBGreaterThan(String value) {
            addCriterion("XZKHJB >", value, "XZKHJB");
            return (Criteria) this;
        }

        public Criteria andXZKHJBGreaterThanOrEqualTo(String value) {
            addCriterion("XZKHJB >=", value, "XZKHJB");
            return (Criteria) this;
        }

        public Criteria andXZKHJBLessThan(String value) {
            addCriterion("XZKHJB <", value, "XZKHJB");
            return (Criteria) this;
        }

        public Criteria andXZKHJBLessThanOrEqualTo(String value) {
            addCriterion("XZKHJB <=", value, "XZKHJB");
            return (Criteria) this;
        }

        public Criteria andXZKHJBLike(String value) {
            addCriterion("XZKHJB like", value, "XZKHJB");
            return (Criteria) this;
        }

        public Criteria andXZKHJBNotLike(String value) {
            addCriterion("XZKHJB not like", value, "XZKHJB");
            return (Criteria) this;
        }

        public Criteria andXZKHJBIn(List<String> values) {
            addCriterion("XZKHJB in", values, "XZKHJB");
            return (Criteria) this;
        }

        public Criteria andXZKHJBNotIn(List<String> values) {
            addCriterion("XZKHJB not in", values, "XZKHJB");
            return (Criteria) this;
        }

        public Criteria andXZKHJBBetween(String value1, String value2) {
            addCriterion("XZKHJB between", value1, value2, "XZKHJB");
            return (Criteria) this;
        }

        public Criteria andXZKHJBNotBetween(String value1, String value2) {
            addCriterion("XZKHJB not between", value1, value2, "XZKHJB");
            return (Criteria) this;
        }

        public Criteria andBZXX_UIsNull() {
            addCriterion("BZXX_U is null");
            return (Criteria) this;
        }

        public Criteria andBZXX_UIsNotNull() {
            addCriterion("BZXX_U is not null");
            return (Criteria) this;
        }

        public Criteria andBZXX_UEqualTo(String value) {
            addCriterion("BZXX_U =", value, "BZXX_U");
            return (Criteria) this;
        }

        public Criteria andBZXX_UNotEqualTo(String value) {
            addCriterion("BZXX_U <>", value, "BZXX_U");
            return (Criteria) this;
        }

        public Criteria andBZXX_UGreaterThan(String value) {
            addCriterion("BZXX_U >", value, "BZXX_U");
            return (Criteria) this;
        }

        public Criteria andBZXX_UGreaterThanOrEqualTo(String value) {
            addCriterion("BZXX_U >=", value, "BZXX_U");
            return (Criteria) this;
        }

        public Criteria andBZXX_ULessThan(String value) {
            addCriterion("BZXX_U <", value, "BZXX_U");
            return (Criteria) this;
        }

        public Criteria andBZXX_ULessThanOrEqualTo(String value) {
            addCriterion("BZXX_U <=", value, "BZXX_U");
            return (Criteria) this;
        }

        public Criteria andBZXX_ULike(String value) {
            addCriterion("BZXX_U like", value, "BZXX_U");
            return (Criteria) this;
        }

        public Criteria andBZXX_UNotLike(String value) {
            addCriterion("BZXX_U not like", value, "BZXX_U");
            return (Criteria) this;
        }

        public Criteria andBZXX_UIn(List<String> values) {
            addCriterion("BZXX_U in", values, "BZXX_U");
            return (Criteria) this;
        }

        public Criteria andBZXX_UNotIn(List<String> values) {
            addCriterion("BZXX_U not in", values, "BZXX_U");
            return (Criteria) this;
        }

        public Criteria andBZXX_UBetween(String value1, String value2) {
            addCriterion("BZXX_U between", value1, value2, "BZXX_U");
            return (Criteria) this;
        }

        public Criteria andBZXX_UNotBetween(String value1, String value2) {
            addCriterion("BZXX_U not between", value1, value2, "BZXX_U");
            return (Criteria) this;
        }

        public Criteria andCJRQ_UIsNull() {
            addCriterion("CJRQ_U is null");
            return (Criteria) this;
        }

        public Criteria andCJRQ_UIsNotNull() {
            addCriterion("CJRQ_U is not null");
            return (Criteria) this;
        }

        public Criteria andCJRQ_UEqualTo(Date value) {
            addCriterionForJDBCDate("CJRQ_U =", value, "CJRQ_U");
            return (Criteria) this;
        }

        public Criteria andCJRQ_UNotEqualTo(Date value) {
            addCriterionForJDBCDate("CJRQ_U <>", value, "CJRQ_U");
            return (Criteria) this;
        }

        public Criteria andCJRQ_UGreaterThan(Date value) {
            addCriterionForJDBCDate("CJRQ_U >", value, "CJRQ_U");
            return (Criteria) this;
        }

        public Criteria andCJRQ_UGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CJRQ_U >=", value, "CJRQ_U");
            return (Criteria) this;
        }

        public Criteria andCJRQ_ULessThan(Date value) {
            addCriterionForJDBCDate("CJRQ_U <", value, "CJRQ_U");
            return (Criteria) this;
        }

        public Criteria andCJRQ_ULessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CJRQ_U <=", value, "CJRQ_U");
            return (Criteria) this;
        }

        public Criteria andCJRQ_UIn(List<Date> values) {
            addCriterionForJDBCDate("CJRQ_U in", values, "CJRQ_U");
            return (Criteria) this;
        }

        public Criteria andCJRQ_UNotIn(List<Date> values) {
            addCriterionForJDBCDate("CJRQ_U not in", values, "CJRQ_U");
            return (Criteria) this;
        }

        public Criteria andCJRQ_UBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CJRQ_U between", value1, value2, "CJRQ_U");
            return (Criteria) this;
        }

        public Criteria andCJRQ_UNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CJRQ_U not between", value1, value2, "CJRQ_U");
            return (Criteria) this;
        }

        public Criteria andCJSJ_UIsNull() {
            addCriterion("CJSJ_U is null");
            return (Criteria) this;
        }

        public Criteria andCJSJ_UIsNotNull() {
            addCriterion("CJSJ_U is not null");
            return (Criteria) this;
        }

        public Criteria andCJSJ_UEqualTo(Date value) {
            addCriterionForJDBCTime("CJSJ_U =", value, "CJSJ_U");
            return (Criteria) this;
        }

        public Criteria andCJSJ_UNotEqualTo(Date value) {
            addCriterionForJDBCTime("CJSJ_U <>", value, "CJSJ_U");
            return (Criteria) this;
        }

        public Criteria andCJSJ_UGreaterThan(Date value) {
            addCriterionForJDBCTime("CJSJ_U >", value, "CJSJ_U");
            return (Criteria) this;
        }

        public Criteria andCJSJ_UGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("CJSJ_U >=", value, "CJSJ_U");
            return (Criteria) this;
        }

        public Criteria andCJSJ_ULessThan(Date value) {
            addCriterionForJDBCTime("CJSJ_U <", value, "CJSJ_U");
            return (Criteria) this;
        }

        public Criteria andCJSJ_ULessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("CJSJ_U <=", value, "CJSJ_U");
            return (Criteria) this;
        }

        public Criteria andCJSJ_UIn(List<Date> values) {
            addCriterionForJDBCTime("CJSJ_U in", values, "CJSJ_U");
            return (Criteria) this;
        }

        public Criteria andCJSJ_UNotIn(List<Date> values) {
            addCriterionForJDBCTime("CJSJ_U not in", values, "CJSJ_U");
            return (Criteria) this;
        }

        public Criteria andCJSJ_UBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("CJSJ_U between", value1, value2, "CJSJ_U");
            return (Criteria) this;
        }

        public Criteria andCJSJ_UNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("CJSJ_U not between", value1, value2, "CJSJ_U");
            return (Criteria) this;
        }

        public Criteria andCJR_UUIsNull() {
            addCriterion("CJR_UU is null");
            return (Criteria) this;
        }

        public Criteria andCJR_UUIsNotNull() {
            addCriterion("CJR_UU is not null");
            return (Criteria) this;
        }

        public Criteria andCJR_UUEqualTo(String value) {
            addCriterion("CJR_UU =", value, "CJR_UU");
            return (Criteria) this;
        }

        public Criteria andCJR_UUNotEqualTo(String value) {
            addCriterion("CJR_UU <>", value, "CJR_UU");
            return (Criteria) this;
        }

        public Criteria andCJR_UUGreaterThan(String value) {
            addCriterion("CJR_UU >", value, "CJR_UU");
            return (Criteria) this;
        }

        public Criteria andCJR_UUGreaterThanOrEqualTo(String value) {
            addCriterion("CJR_UU >=", value, "CJR_UU");
            return (Criteria) this;
        }

        public Criteria andCJR_UULessThan(String value) {
            addCriterion("CJR_UU <", value, "CJR_UU");
            return (Criteria) this;
        }

        public Criteria andCJR_UULessThanOrEqualTo(String value) {
            addCriterion("CJR_UU <=", value, "CJR_UU");
            return (Criteria) this;
        }

        public Criteria andCJR_UULike(String value) {
            addCriterion("CJR_UU like", value, "CJR_UU");
            return (Criteria) this;
        }

        public Criteria andCJR_UUNotLike(String value) {
            addCriterion("CJR_UU not like", value, "CJR_UU");
            return (Criteria) this;
        }

        public Criteria andCJR_UUIn(List<String> values) {
            addCriterion("CJR_UU in", values, "CJR_UU");
            return (Criteria) this;
        }

        public Criteria andCJR_UUNotIn(List<String> values) {
            addCriterion("CJR_UU not in", values, "CJR_UU");
            return (Criteria) this;
        }

        public Criteria andCJR_UUBetween(String value1, String value2) {
            addCriterion("CJR_UU between", value1, value2, "CJR_UU");
            return (Criteria) this;
        }

        public Criteria andCJR_UUNotBetween(String value1, String value2) {
            addCriterion("CJR_UU not between", value1, value2, "CJR_UU");
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