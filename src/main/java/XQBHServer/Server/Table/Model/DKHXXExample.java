package XQBHServer.Server.Table.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DKHXXExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DKHXXExample() {
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

        public Criteria andKHBH_UIsNull() {
            addCriterion("KHBH_U is null");
            return (Criteria) this;
        }

        public Criteria andKHBH_UIsNotNull() {
            addCriterion("KHBH_U is not null");
            return (Criteria) this;
        }

        public Criteria andKHBH_UEqualTo(String value) {
            addCriterion("KHBH_U =", value, "KHBH_U");
            return (Criteria) this;
        }

        public Criteria andKHBH_UNotEqualTo(String value) {
            addCriterion("KHBH_U <>", value, "KHBH_U");
            return (Criteria) this;
        }

        public Criteria andKHBH_UGreaterThan(String value) {
            addCriterion("KHBH_U >", value, "KHBH_U");
            return (Criteria) this;
        }

        public Criteria andKHBH_UGreaterThanOrEqualTo(String value) {
            addCriterion("KHBH_U >=", value, "KHBH_U");
            return (Criteria) this;
        }

        public Criteria andKHBH_ULessThan(String value) {
            addCriterion("KHBH_U <", value, "KHBH_U");
            return (Criteria) this;
        }

        public Criteria andKHBH_ULessThanOrEqualTo(String value) {
            addCriterion("KHBH_U <=", value, "KHBH_U");
            return (Criteria) this;
        }

        public Criteria andKHBH_ULike(String value) {
            addCriterion("KHBH_U like", value, "KHBH_U");
            return (Criteria) this;
        }

        public Criteria andKHBH_UNotLike(String value) {
            addCriterion("KHBH_U not like", value, "KHBH_U");
            return (Criteria) this;
        }

        public Criteria andKHBH_UIn(List<String> values) {
            addCriterion("KHBH_U in", values, "KHBH_U");
            return (Criteria) this;
        }

        public Criteria andKHBH_UNotIn(List<String> values) {
            addCriterion("KHBH_U not in", values, "KHBH_U");
            return (Criteria) this;
        }

        public Criteria andKHBH_UBetween(String value1, String value2) {
            addCriterion("KHBH_U between", value1, value2, "KHBH_U");
            return (Criteria) this;
        }

        public Criteria andKHBH_UNotBetween(String value1, String value2) {
            addCriterion("KHBH_U not between", value1, value2, "KHBH_U");
            return (Criteria) this;
        }

        public Criteria andKHMC_UIsNull() {
            addCriterion("KHMC_U is null");
            return (Criteria) this;
        }

        public Criteria andKHMC_UIsNotNull() {
            addCriterion("KHMC_U is not null");
            return (Criteria) this;
        }

        public Criteria andKHMC_UEqualTo(String value) {
            addCriterion("KHMC_U =", value, "KHMC_U");
            return (Criteria) this;
        }

        public Criteria andKHMC_UNotEqualTo(String value) {
            addCriterion("KHMC_U <>", value, "KHMC_U");
            return (Criteria) this;
        }

        public Criteria andKHMC_UGreaterThan(String value) {
            addCriterion("KHMC_U >", value, "KHMC_U");
            return (Criteria) this;
        }

        public Criteria andKHMC_UGreaterThanOrEqualTo(String value) {
            addCriterion("KHMC_U >=", value, "KHMC_U");
            return (Criteria) this;
        }

        public Criteria andKHMC_ULessThan(String value) {
            addCriterion("KHMC_U <", value, "KHMC_U");
            return (Criteria) this;
        }

        public Criteria andKHMC_ULessThanOrEqualTo(String value) {
            addCriterion("KHMC_U <=", value, "KHMC_U");
            return (Criteria) this;
        }

        public Criteria andKHMC_ULike(String value) {
            addCriterion("KHMC_U like", value, "KHMC_U");
            return (Criteria) this;
        }

        public Criteria andKHMC_UNotLike(String value) {
            addCriterion("KHMC_U not like", value, "KHMC_U");
            return (Criteria) this;
        }

        public Criteria andKHMC_UIn(List<String> values) {
            addCriterion("KHMC_U in", values, "KHMC_U");
            return (Criteria) this;
        }

        public Criteria andKHMC_UNotIn(List<String> values) {
            addCriterion("KHMC_U not in", values, "KHMC_U");
            return (Criteria) this;
        }

        public Criteria andKHMC_UBetween(String value1, String value2) {
            addCriterion("KHMC_U between", value1, value2, "KHMC_U");
            return (Criteria) this;
        }

        public Criteria andKHMC_UNotBetween(String value1, String value2) {
            addCriterion("KHMC_U not between", value1, value2, "KHMC_U");
            return (Criteria) this;
        }

        public Criteria andKHDJ_UIsNull() {
            addCriterion("KHDJ_U is null");
            return (Criteria) this;
        }

        public Criteria andKHDJ_UIsNotNull() {
            addCriterion("KHDJ_U is not null");
            return (Criteria) this;
        }

        public Criteria andKHDJ_UEqualTo(String value) {
            addCriterion("KHDJ_U =", value, "KHDJ_U");
            return (Criteria) this;
        }

        public Criteria andKHDJ_UNotEqualTo(String value) {
            addCriterion("KHDJ_U <>", value, "KHDJ_U");
            return (Criteria) this;
        }

        public Criteria andKHDJ_UGreaterThan(String value) {
            addCriterion("KHDJ_U >", value, "KHDJ_U");
            return (Criteria) this;
        }

        public Criteria andKHDJ_UGreaterThanOrEqualTo(String value) {
            addCriterion("KHDJ_U >=", value, "KHDJ_U");
            return (Criteria) this;
        }

        public Criteria andKHDJ_ULessThan(String value) {
            addCriterion("KHDJ_U <", value, "KHDJ_U");
            return (Criteria) this;
        }

        public Criteria andKHDJ_ULessThanOrEqualTo(String value) {
            addCriterion("KHDJ_U <=", value, "KHDJ_U");
            return (Criteria) this;
        }

        public Criteria andKHDJ_ULike(String value) {
            addCriterion("KHDJ_U like", value, "KHDJ_U");
            return (Criteria) this;
        }

        public Criteria andKHDJ_UNotLike(String value) {
            addCriterion("KHDJ_U not like", value, "KHDJ_U");
            return (Criteria) this;
        }

        public Criteria andKHDJ_UIn(List<String> values) {
            addCriterion("KHDJ_U in", values, "KHDJ_U");
            return (Criteria) this;
        }

        public Criteria andKHDJ_UNotIn(List<String> values) {
            addCriterion("KHDJ_U not in", values, "KHDJ_U");
            return (Criteria) this;
        }

        public Criteria andKHDJ_UBetween(String value1, String value2) {
            addCriterion("KHDJ_U between", value1, value2, "KHDJ_U");
            return (Criteria) this;
        }

        public Criteria andKHDJ_UNotBetween(String value1, String value2) {
            addCriterion("KHDJ_U not between", value1, value2, "KHDJ_U");
            return (Criteria) this;
        }

        public Criteria andIDNO_UIsNull() {
            addCriterion("IDNO_U is null");
            return (Criteria) this;
        }

        public Criteria andIDNO_UIsNotNull() {
            addCriterion("IDNO_U is not null");
            return (Criteria) this;
        }

        public Criteria andIDNO_UEqualTo(String value) {
            addCriterion("IDNO_U =", value, "IDNO_U");
            return (Criteria) this;
        }

        public Criteria andIDNO_UNotEqualTo(String value) {
            addCriterion("IDNO_U <>", value, "IDNO_U");
            return (Criteria) this;
        }

        public Criteria andIDNO_UGreaterThan(String value) {
            addCriterion("IDNO_U >", value, "IDNO_U");
            return (Criteria) this;
        }

        public Criteria andIDNO_UGreaterThanOrEqualTo(String value) {
            addCriterion("IDNO_U >=", value, "IDNO_U");
            return (Criteria) this;
        }

        public Criteria andIDNO_ULessThan(String value) {
            addCriterion("IDNO_U <", value, "IDNO_U");
            return (Criteria) this;
        }

        public Criteria andIDNO_ULessThanOrEqualTo(String value) {
            addCriterion("IDNO_U <=", value, "IDNO_U");
            return (Criteria) this;
        }

        public Criteria andIDNO_ULike(String value) {
            addCriterion("IDNO_U like", value, "IDNO_U");
            return (Criteria) this;
        }

        public Criteria andIDNO_UNotLike(String value) {
            addCriterion("IDNO_U not like", value, "IDNO_U");
            return (Criteria) this;
        }

        public Criteria andIDNO_UIn(List<String> values) {
            addCriterion("IDNO_U in", values, "IDNO_U");
            return (Criteria) this;
        }

        public Criteria andIDNO_UNotIn(List<String> values) {
            addCriterion("IDNO_U not in", values, "IDNO_U");
            return (Criteria) this;
        }

        public Criteria andIDNO_UBetween(String value1, String value2) {
            addCriterion("IDNO_U between", value1, value2, "IDNO_U");
            return (Criteria) this;
        }

        public Criteria andIDNO_UNotBetween(String value1, String value2) {
            addCriterion("IDNO_U not between", value1, value2, "IDNO_U");
            return (Criteria) this;
        }

        public Criteria andDZ_UUUIsNull() {
            addCriterion("DZ_UUU is null");
            return (Criteria) this;
        }

        public Criteria andDZ_UUUIsNotNull() {
            addCriterion("DZ_UUU is not null");
            return (Criteria) this;
        }

        public Criteria andDZ_UUUEqualTo(String value) {
            addCriterion("DZ_UUU =", value, "DZ_UUU");
            return (Criteria) this;
        }

        public Criteria andDZ_UUUNotEqualTo(String value) {
            addCriterion("DZ_UUU <>", value, "DZ_UUU");
            return (Criteria) this;
        }

        public Criteria andDZ_UUUGreaterThan(String value) {
            addCriterion("DZ_UUU >", value, "DZ_UUU");
            return (Criteria) this;
        }

        public Criteria andDZ_UUUGreaterThanOrEqualTo(String value) {
            addCriterion("DZ_UUU >=", value, "DZ_UUU");
            return (Criteria) this;
        }

        public Criteria andDZ_UUULessThan(String value) {
            addCriterion("DZ_UUU <", value, "DZ_UUU");
            return (Criteria) this;
        }

        public Criteria andDZ_UUULessThanOrEqualTo(String value) {
            addCriterion("DZ_UUU <=", value, "DZ_UUU");
            return (Criteria) this;
        }

        public Criteria andDZ_UUULike(String value) {
            addCriterion("DZ_UUU like", value, "DZ_UUU");
            return (Criteria) this;
        }

        public Criteria andDZ_UUUNotLike(String value) {
            addCriterion("DZ_UUU not like", value, "DZ_UUU");
            return (Criteria) this;
        }

        public Criteria andDZ_UUUIn(List<String> values) {
            addCriterion("DZ_UUU in", values, "DZ_UUU");
            return (Criteria) this;
        }

        public Criteria andDZ_UUUNotIn(List<String> values) {
            addCriterion("DZ_UUU not in", values, "DZ_UUU");
            return (Criteria) this;
        }

        public Criteria andDZ_UUUBetween(String value1, String value2) {
            addCriterion("DZ_UUU between", value1, value2, "DZ_UUU");
            return (Criteria) this;
        }

        public Criteria andDZ_UUUNotBetween(String value1, String value2) {
            addCriterion("DZ_UUU not between", value1, value2, "DZ_UUU");
            return (Criteria) this;
        }

        public Criteria andSXRQ_UIsNull() {
            addCriterion("SXRQ_U is null");
            return (Criteria) this;
        }

        public Criteria andSXRQ_UIsNotNull() {
            addCriterion("SXRQ_U is not null");
            return (Criteria) this;
        }

        public Criteria andSXRQ_UEqualTo(Date value) {
            addCriterionForJDBCDate("SXRQ_U =", value, "SXRQ_U");
            return (Criteria) this;
        }

        public Criteria andSXRQ_UNotEqualTo(Date value) {
            addCriterionForJDBCDate("SXRQ_U <>", value, "SXRQ_U");
            return (Criteria) this;
        }

        public Criteria andSXRQ_UGreaterThan(Date value) {
            addCriterionForJDBCDate("SXRQ_U >", value, "SXRQ_U");
            return (Criteria) this;
        }

        public Criteria andSXRQ_UGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SXRQ_U >=", value, "SXRQ_U");
            return (Criteria) this;
        }

        public Criteria andSXRQ_ULessThan(Date value) {
            addCriterionForJDBCDate("SXRQ_U <", value, "SXRQ_U");
            return (Criteria) this;
        }

        public Criteria andSXRQ_ULessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SXRQ_U <=", value, "SXRQ_U");
            return (Criteria) this;
        }

        public Criteria andSXRQ_UIn(List<Date> values) {
            addCriterionForJDBCDate("SXRQ_U in", values, "SXRQ_U");
            return (Criteria) this;
        }

        public Criteria andSXRQ_UNotIn(List<Date> values) {
            addCriterionForJDBCDate("SXRQ_U not in", values, "SXRQ_U");
            return (Criteria) this;
        }

        public Criteria andSXRQ_UBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SXRQ_U between", value1, value2, "SXRQ_U");
            return (Criteria) this;
        }

        public Criteria andSXRQ_UNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SXRQ_U not between", value1, value2, "SXRQ_U");
            return (Criteria) this;
        }

        public Criteria andGQRQ_UIsNull() {
            addCriterion("GQRQ_U is null");
            return (Criteria) this;
        }

        public Criteria andGQRQ_UIsNotNull() {
            addCriterion("GQRQ_U is not null");
            return (Criteria) this;
        }

        public Criteria andGQRQ_UEqualTo(Date value) {
            addCriterionForJDBCDate("GQRQ_U =", value, "GQRQ_U");
            return (Criteria) this;
        }

        public Criteria andGQRQ_UNotEqualTo(Date value) {
            addCriterionForJDBCDate("GQRQ_U <>", value, "GQRQ_U");
            return (Criteria) this;
        }

        public Criteria andGQRQ_UGreaterThan(Date value) {
            addCriterionForJDBCDate("GQRQ_U >", value, "GQRQ_U");
            return (Criteria) this;
        }

        public Criteria andGQRQ_UGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("GQRQ_U >=", value, "GQRQ_U");
            return (Criteria) this;
        }

        public Criteria andGQRQ_ULessThan(Date value) {
            addCriterionForJDBCDate("GQRQ_U <", value, "GQRQ_U");
            return (Criteria) this;
        }

        public Criteria andGQRQ_ULessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("GQRQ_U <=", value, "GQRQ_U");
            return (Criteria) this;
        }

        public Criteria andGQRQ_UIn(List<Date> values) {
            addCriterionForJDBCDate("GQRQ_U in", values, "GQRQ_U");
            return (Criteria) this;
        }

        public Criteria andGQRQ_UNotIn(List<Date> values) {
            addCriterionForJDBCDate("GQRQ_U not in", values, "GQRQ_U");
            return (Criteria) this;
        }

        public Criteria andGQRQ_UBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("GQRQ_U between", value1, value2, "GQRQ_U");
            return (Criteria) this;
        }

        public Criteria andGQRQ_UNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("GQRQ_U not between", value1, value2, "GQRQ_U");
            return (Criteria) this;
        }

        public Criteria andCSRQ_UIsNull() {
            addCriterion("CSRQ_U is null");
            return (Criteria) this;
        }

        public Criteria andCSRQ_UIsNotNull() {
            addCriterion("CSRQ_U is not null");
            return (Criteria) this;
        }

        public Criteria andCSRQ_UEqualTo(Date value) {
            addCriterionForJDBCDate("CSRQ_U =", value, "CSRQ_U");
            return (Criteria) this;
        }

        public Criteria andCSRQ_UNotEqualTo(Date value) {
            addCriterionForJDBCDate("CSRQ_U <>", value, "CSRQ_U");
            return (Criteria) this;
        }

        public Criteria andCSRQ_UGreaterThan(Date value) {
            addCriterionForJDBCDate("CSRQ_U >", value, "CSRQ_U");
            return (Criteria) this;
        }

        public Criteria andCSRQ_UGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CSRQ_U >=", value, "CSRQ_U");
            return (Criteria) this;
        }

        public Criteria andCSRQ_ULessThan(Date value) {
            addCriterionForJDBCDate("CSRQ_U <", value, "CSRQ_U");
            return (Criteria) this;
        }

        public Criteria andCSRQ_ULessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CSRQ_U <=", value, "CSRQ_U");
            return (Criteria) this;
        }

        public Criteria andCSRQ_UIn(List<Date> values) {
            addCriterionForJDBCDate("CSRQ_U in", values, "CSRQ_U");
            return (Criteria) this;
        }

        public Criteria andCSRQ_UNotIn(List<Date> values) {
            addCriterionForJDBCDate("CSRQ_U not in", values, "CSRQ_U");
            return (Criteria) this;
        }

        public Criteria andCSRQ_UBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CSRQ_U between", value1, value2, "CSRQ_U");
            return (Criteria) this;
        }

        public Criteria andCSRQ_UNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CSRQ_U not between", value1, value2, "CSRQ_U");
            return (Criteria) this;
        }

        public Criteria andSJH_UUIsNull() {
            addCriterion("SJH_UU is null");
            return (Criteria) this;
        }

        public Criteria andSJH_UUIsNotNull() {
            addCriterion("SJH_UU is not null");
            return (Criteria) this;
        }

        public Criteria andSJH_UUEqualTo(String value) {
            addCriterion("SJH_UU =", value, "SJH_UU");
            return (Criteria) this;
        }

        public Criteria andSJH_UUNotEqualTo(String value) {
            addCriterion("SJH_UU <>", value, "SJH_UU");
            return (Criteria) this;
        }

        public Criteria andSJH_UUGreaterThan(String value) {
            addCriterion("SJH_UU >", value, "SJH_UU");
            return (Criteria) this;
        }

        public Criteria andSJH_UUGreaterThanOrEqualTo(String value) {
            addCriterion("SJH_UU >=", value, "SJH_UU");
            return (Criteria) this;
        }

        public Criteria andSJH_UULessThan(String value) {
            addCriterion("SJH_UU <", value, "SJH_UU");
            return (Criteria) this;
        }

        public Criteria andSJH_UULessThanOrEqualTo(String value) {
            addCriterion("SJH_UU <=", value, "SJH_UU");
            return (Criteria) this;
        }

        public Criteria andSJH_UULike(String value) {
            addCriterion("SJH_UU like", value, "SJH_UU");
            return (Criteria) this;
        }

        public Criteria andSJH_UUNotLike(String value) {
            addCriterion("SJH_UU not like", value, "SJH_UU");
            return (Criteria) this;
        }

        public Criteria andSJH_UUIn(List<String> values) {
            addCriterion("SJH_UU in", values, "SJH_UU");
            return (Criteria) this;
        }

        public Criteria andSJH_UUNotIn(List<String> values) {
            addCriterion("SJH_UU not in", values, "SJH_UU");
            return (Criteria) this;
        }

        public Criteria andSJH_UUBetween(String value1, String value2) {
            addCriterion("SJH_UU between", value1, value2, "SJH_UU");
            return (Criteria) this;
        }

        public Criteria andSJH_UUNotBetween(String value1, String value2) {
            addCriterion("SJH_UU not between", value1, value2, "SJH_UU");
            return (Criteria) this;
        }

        public Criteria andZJ_UUUIsNull() {
            addCriterion("ZJ_UUU is null");
            return (Criteria) this;
        }

        public Criteria andZJ_UUUIsNotNull() {
            addCriterion("ZJ_UUU is not null");
            return (Criteria) this;
        }

        public Criteria andZJ_UUUEqualTo(String value) {
            addCriterion("ZJ_UUU =", value, "ZJ_UUU");
            return (Criteria) this;
        }

        public Criteria andZJ_UUUNotEqualTo(String value) {
            addCriterion("ZJ_UUU <>", value, "ZJ_UUU");
            return (Criteria) this;
        }

        public Criteria andZJ_UUUGreaterThan(String value) {
            addCriterion("ZJ_UUU >", value, "ZJ_UUU");
            return (Criteria) this;
        }

        public Criteria andZJ_UUUGreaterThanOrEqualTo(String value) {
            addCriterion("ZJ_UUU >=", value, "ZJ_UUU");
            return (Criteria) this;
        }

        public Criteria andZJ_UUULessThan(String value) {
            addCriterion("ZJ_UUU <", value, "ZJ_UUU");
            return (Criteria) this;
        }

        public Criteria andZJ_UUULessThanOrEqualTo(String value) {
            addCriterion("ZJ_UUU <=", value, "ZJ_UUU");
            return (Criteria) this;
        }

        public Criteria andZJ_UUULike(String value) {
            addCriterion("ZJ_UUU like", value, "ZJ_UUU");
            return (Criteria) this;
        }

        public Criteria andZJ_UUUNotLike(String value) {
            addCriterion("ZJ_UUU not like", value, "ZJ_UUU");
            return (Criteria) this;
        }

        public Criteria andZJ_UUUIn(List<String> values) {
            addCriterion("ZJ_UUU in", values, "ZJ_UUU");
            return (Criteria) this;
        }

        public Criteria andZJ_UUUNotIn(List<String> values) {
            addCriterion("ZJ_UUU not in", values, "ZJ_UUU");
            return (Criteria) this;
        }

        public Criteria andZJ_UUUBetween(String value1, String value2) {
            addCriterion("ZJ_UUU between", value1, value2, "ZJ_UUU");
            return (Criteria) this;
        }

        public Criteria andZJ_UUUNotBetween(String value1, String value2) {
            addCriterion("ZJ_UUU not between", value1, value2, "ZJ_UUU");
            return (Criteria) this;
        }

        public Criteria andJJLXDHIsNull() {
            addCriterion("JJLXDH is null");
            return (Criteria) this;
        }

        public Criteria andJJLXDHIsNotNull() {
            addCriterion("JJLXDH is not null");
            return (Criteria) this;
        }

        public Criteria andJJLXDHEqualTo(String value) {
            addCriterion("JJLXDH =", value, "JJLXDH");
            return (Criteria) this;
        }

        public Criteria andJJLXDHNotEqualTo(String value) {
            addCriterion("JJLXDH <>", value, "JJLXDH");
            return (Criteria) this;
        }

        public Criteria andJJLXDHGreaterThan(String value) {
            addCriterion("JJLXDH >", value, "JJLXDH");
            return (Criteria) this;
        }

        public Criteria andJJLXDHGreaterThanOrEqualTo(String value) {
            addCriterion("JJLXDH >=", value, "JJLXDH");
            return (Criteria) this;
        }

        public Criteria andJJLXDHLessThan(String value) {
            addCriterion("JJLXDH <", value, "JJLXDH");
            return (Criteria) this;
        }

        public Criteria andJJLXDHLessThanOrEqualTo(String value) {
            addCriterion("JJLXDH <=", value, "JJLXDH");
            return (Criteria) this;
        }

        public Criteria andJJLXDHLike(String value) {
            addCriterion("JJLXDH like", value, "JJLXDH");
            return (Criteria) this;
        }

        public Criteria andJJLXDHNotLike(String value) {
            addCriterion("JJLXDH not like", value, "JJLXDH");
            return (Criteria) this;
        }

        public Criteria andJJLXDHIn(List<String> values) {
            addCriterion("JJLXDH in", values, "JJLXDH");
            return (Criteria) this;
        }

        public Criteria andJJLXDHNotIn(List<String> values) {
            addCriterion("JJLXDH not in", values, "JJLXDH");
            return (Criteria) this;
        }

        public Criteria andJJLXDHBetween(String value1, String value2) {
            addCriterion("JJLXDH between", value1, value2, "JJLXDH");
            return (Criteria) this;
        }

        public Criteria andJJLXDHNotBetween(String value1, String value2) {
            addCriterion("JJLXDH not between", value1, value2, "JJLXDH");
            return (Criteria) this;
        }

        public Criteria andWXH_UUIsNull() {
            addCriterion("WXH_UU is null");
            return (Criteria) this;
        }

        public Criteria andWXH_UUIsNotNull() {
            addCriterion("WXH_UU is not null");
            return (Criteria) this;
        }

        public Criteria andWXH_UUEqualTo(String value) {
            addCriterion("WXH_UU =", value, "WXH_UU");
            return (Criteria) this;
        }

        public Criteria andWXH_UUNotEqualTo(String value) {
            addCriterion("WXH_UU <>", value, "WXH_UU");
            return (Criteria) this;
        }

        public Criteria andWXH_UUGreaterThan(String value) {
            addCriterion("WXH_UU >", value, "WXH_UU");
            return (Criteria) this;
        }

        public Criteria andWXH_UUGreaterThanOrEqualTo(String value) {
            addCriterion("WXH_UU >=", value, "WXH_UU");
            return (Criteria) this;
        }

        public Criteria andWXH_UULessThan(String value) {
            addCriterion("WXH_UU <", value, "WXH_UU");
            return (Criteria) this;
        }

        public Criteria andWXH_UULessThanOrEqualTo(String value) {
            addCriterion("WXH_UU <=", value, "WXH_UU");
            return (Criteria) this;
        }

        public Criteria andWXH_UULike(String value) {
            addCriterion("WXH_UU like", value, "WXH_UU");
            return (Criteria) this;
        }

        public Criteria andWXH_UUNotLike(String value) {
            addCriterion("WXH_UU not like", value, "WXH_UU");
            return (Criteria) this;
        }

        public Criteria andWXH_UUIn(List<String> values) {
            addCriterion("WXH_UU in", values, "WXH_UU");
            return (Criteria) this;
        }

        public Criteria andWXH_UUNotIn(List<String> values) {
            addCriterion("WXH_UU not in", values, "WXH_UU");
            return (Criteria) this;
        }

        public Criteria andWXH_UUBetween(String value1, String value2) {
            addCriterion("WXH_UU between", value1, value2, "WXH_UU");
            return (Criteria) this;
        }

        public Criteria andWXH_UUNotBetween(String value1, String value2) {
            addCriterion("WXH_UU not between", value1, value2, "WXH_UU");
            return (Criteria) this;
        }

        public Criteria andZFBH_UIsNull() {
            addCriterion("ZFBH_U is null");
            return (Criteria) this;
        }

        public Criteria andZFBH_UIsNotNull() {
            addCriterion("ZFBH_U is not null");
            return (Criteria) this;
        }

        public Criteria andZFBH_UEqualTo(String value) {
            addCriterion("ZFBH_U =", value, "ZFBH_U");
            return (Criteria) this;
        }

        public Criteria andZFBH_UNotEqualTo(String value) {
            addCriterion("ZFBH_U <>", value, "ZFBH_U");
            return (Criteria) this;
        }

        public Criteria andZFBH_UGreaterThan(String value) {
            addCriterion("ZFBH_U >", value, "ZFBH_U");
            return (Criteria) this;
        }

        public Criteria andZFBH_UGreaterThanOrEqualTo(String value) {
            addCriterion("ZFBH_U >=", value, "ZFBH_U");
            return (Criteria) this;
        }

        public Criteria andZFBH_ULessThan(String value) {
            addCriterion("ZFBH_U <", value, "ZFBH_U");
            return (Criteria) this;
        }

        public Criteria andZFBH_ULessThanOrEqualTo(String value) {
            addCriterion("ZFBH_U <=", value, "ZFBH_U");
            return (Criteria) this;
        }

        public Criteria andZFBH_ULike(String value) {
            addCriterion("ZFBH_U like", value, "ZFBH_U");
            return (Criteria) this;
        }

        public Criteria andZFBH_UNotLike(String value) {
            addCriterion("ZFBH_U not like", value, "ZFBH_U");
            return (Criteria) this;
        }

        public Criteria andZFBH_UIn(List<String> values) {
            addCriterion("ZFBH_U in", values, "ZFBH_U");
            return (Criteria) this;
        }

        public Criteria andZFBH_UNotIn(List<String> values) {
            addCriterion("ZFBH_U not in", values, "ZFBH_U");
            return (Criteria) this;
        }

        public Criteria andZFBH_UBetween(String value1, String value2) {
            addCriterion("ZFBH_U between", value1, value2, "ZFBH_U");
            return (Criteria) this;
        }

        public Criteria andZFBH_UNotBetween(String value1, String value2) {
            addCriterion("ZFBH_U not between", value1, value2, "ZFBH_U");
            return (Criteria) this;
        }

        public Criteria andEMAIL_IsNull() {
            addCriterion("EMAIL_ is null");
            return (Criteria) this;
        }

        public Criteria andEMAIL_IsNotNull() {
            addCriterion("EMAIL_ is not null");
            return (Criteria) this;
        }

        public Criteria andEMAIL_EqualTo(String value) {
            addCriterion("EMAIL_ =", value, "EMAIL_");
            return (Criteria) this;
        }

        public Criteria andEMAIL_NotEqualTo(String value) {
            addCriterion("EMAIL_ <>", value, "EMAIL_");
            return (Criteria) this;
        }

        public Criteria andEMAIL_GreaterThan(String value) {
            addCriterion("EMAIL_ >", value, "EMAIL_");
            return (Criteria) this;
        }

        public Criteria andEMAIL_GreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL_ >=", value, "EMAIL_");
            return (Criteria) this;
        }

        public Criteria andEMAIL_LessThan(String value) {
            addCriterion("EMAIL_ <", value, "EMAIL_");
            return (Criteria) this;
        }

        public Criteria andEMAIL_LessThanOrEqualTo(String value) {
            addCriterion("EMAIL_ <=", value, "EMAIL_");
            return (Criteria) this;
        }

        public Criteria andEMAIL_Like(String value) {
            addCriterion("EMAIL_ like", value, "EMAIL_");
            return (Criteria) this;
        }

        public Criteria andEMAIL_NotLike(String value) {
            addCriterion("EMAIL_ not like", value, "EMAIL_");
            return (Criteria) this;
        }

        public Criteria andEMAIL_In(List<String> values) {
            addCriterion("EMAIL_ in", values, "EMAIL_");
            return (Criteria) this;
        }

        public Criteria andEMAIL_NotIn(List<String> values) {
            addCriterion("EMAIL_ not in", values, "EMAIL_");
            return (Criteria) this;
        }

        public Criteria andEMAIL_Between(String value1, String value2) {
            addCriterion("EMAIL_ between", value1, value2, "EMAIL_");
            return (Criteria) this;
        }

        public Criteria andEMAIL_NotBetween(String value1, String value2) {
            addCriterion("EMAIL_ not between", value1, value2, "EMAIL_");
            return (Criteria) this;
        }

        public Criteria andKHDLZHIsNull() {
            addCriterion("KHDLZH is null");
            return (Criteria) this;
        }

        public Criteria andKHDLZHIsNotNull() {
            addCriterion("KHDLZH is not null");
            return (Criteria) this;
        }

        public Criteria andKHDLZHEqualTo(String value) {
            addCriterion("KHDLZH =", value, "KHDLZH");
            return (Criteria) this;
        }

        public Criteria andKHDLZHNotEqualTo(String value) {
            addCriterion("KHDLZH <>", value, "KHDLZH");
            return (Criteria) this;
        }

        public Criteria andKHDLZHGreaterThan(String value) {
            addCriterion("KHDLZH >", value, "KHDLZH");
            return (Criteria) this;
        }

        public Criteria andKHDLZHGreaterThanOrEqualTo(String value) {
            addCriterion("KHDLZH >=", value, "KHDLZH");
            return (Criteria) this;
        }

        public Criteria andKHDLZHLessThan(String value) {
            addCriterion("KHDLZH <", value, "KHDLZH");
            return (Criteria) this;
        }

        public Criteria andKHDLZHLessThanOrEqualTo(String value) {
            addCriterion("KHDLZH <=", value, "KHDLZH");
            return (Criteria) this;
        }

        public Criteria andKHDLZHLike(String value) {
            addCriterion("KHDLZH like", value, "KHDLZH");
            return (Criteria) this;
        }

        public Criteria andKHDLZHNotLike(String value) {
            addCriterion("KHDLZH not like", value, "KHDLZH");
            return (Criteria) this;
        }

        public Criteria andKHDLZHIn(List<String> values) {
            addCriterion("KHDLZH in", values, "KHDLZH");
            return (Criteria) this;
        }

        public Criteria andKHDLZHNotIn(List<String> values) {
            addCriterion("KHDLZH not in", values, "KHDLZH");
            return (Criteria) this;
        }

        public Criteria andKHDLZHBetween(String value1, String value2) {
            addCriterion("KHDLZH between", value1, value2, "KHDLZH");
            return (Criteria) this;
        }

        public Criteria andKHDLZHNotBetween(String value1, String value2) {
            addCriterion("KHDLZH not between", value1, value2, "KHDLZH");
            return (Criteria) this;
        }

        public Criteria andKHMM_UIsNull() {
            addCriterion("KHMM_U is null");
            return (Criteria) this;
        }

        public Criteria andKHMM_UIsNotNull() {
            addCriterion("KHMM_U is not null");
            return (Criteria) this;
        }

        public Criteria andKHMM_UEqualTo(String value) {
            addCriterion("KHMM_U =", value, "KHMM_U");
            return (Criteria) this;
        }

        public Criteria andKHMM_UNotEqualTo(String value) {
            addCriterion("KHMM_U <>", value, "KHMM_U");
            return (Criteria) this;
        }

        public Criteria andKHMM_UGreaterThan(String value) {
            addCriterion("KHMM_U >", value, "KHMM_U");
            return (Criteria) this;
        }

        public Criteria andKHMM_UGreaterThanOrEqualTo(String value) {
            addCriterion("KHMM_U >=", value, "KHMM_U");
            return (Criteria) this;
        }

        public Criteria andKHMM_ULessThan(String value) {
            addCriterion("KHMM_U <", value, "KHMM_U");
            return (Criteria) this;
        }

        public Criteria andKHMM_ULessThanOrEqualTo(String value) {
            addCriterion("KHMM_U <=", value, "KHMM_U");
            return (Criteria) this;
        }

        public Criteria andKHMM_ULike(String value) {
            addCriterion("KHMM_U like", value, "KHMM_U");
            return (Criteria) this;
        }

        public Criteria andKHMM_UNotLike(String value) {
            addCriterion("KHMM_U not like", value, "KHMM_U");
            return (Criteria) this;
        }

        public Criteria andKHMM_UIn(List<String> values) {
            addCriterion("KHMM_U in", values, "KHMM_U");
            return (Criteria) this;
        }

        public Criteria andKHMM_UNotIn(List<String> values) {
            addCriterion("KHMM_U not in", values, "KHMM_U");
            return (Criteria) this;
        }

        public Criteria andKHMM_UBetween(String value1, String value2) {
            addCriterion("KHMM_U between", value1, value2, "KHMM_U");
            return (Criteria) this;
        }

        public Criteria andKHMM_UNotBetween(String value1, String value2) {
            addCriterion("KHMM_U not between", value1, value2, "KHMM_U");
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

        public Criteria andSCDLRQIsNull() {
            addCriterion("SCDLRQ is null");
            return (Criteria) this;
        }

        public Criteria andSCDLRQIsNotNull() {
            addCriterion("SCDLRQ is not null");
            return (Criteria) this;
        }

        public Criteria andSCDLRQEqualTo(Date value) {
            addCriterionForJDBCDate("SCDLRQ =", value, "SCDLRQ");
            return (Criteria) this;
        }

        public Criteria andSCDLRQNotEqualTo(Date value) {
            addCriterionForJDBCDate("SCDLRQ <>", value, "SCDLRQ");
            return (Criteria) this;
        }

        public Criteria andSCDLRQGreaterThan(Date value) {
            addCriterionForJDBCDate("SCDLRQ >", value, "SCDLRQ");
            return (Criteria) this;
        }

        public Criteria andSCDLRQGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SCDLRQ >=", value, "SCDLRQ");
            return (Criteria) this;
        }

        public Criteria andSCDLRQLessThan(Date value) {
            addCriterionForJDBCDate("SCDLRQ <", value, "SCDLRQ");
            return (Criteria) this;
        }

        public Criteria andSCDLRQLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SCDLRQ <=", value, "SCDLRQ");
            return (Criteria) this;
        }

        public Criteria andSCDLRQIn(List<Date> values) {
            addCriterionForJDBCDate("SCDLRQ in", values, "SCDLRQ");
            return (Criteria) this;
        }

        public Criteria andSCDLRQNotIn(List<Date> values) {
            addCriterionForJDBCDate("SCDLRQ not in", values, "SCDLRQ");
            return (Criteria) this;
        }

        public Criteria andSCDLRQBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SCDLRQ between", value1, value2, "SCDLRQ");
            return (Criteria) this;
        }

        public Criteria andSCDLRQNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SCDLRQ not between", value1, value2, "SCDLRQ");
            return (Criteria) this;
        }

        public Criteria andSCDLSJIsNull() {
            addCriterion("SCDLSJ is null");
            return (Criteria) this;
        }

        public Criteria andSCDLSJIsNotNull() {
            addCriterion("SCDLSJ is not null");
            return (Criteria) this;
        }

        public Criteria andSCDLSJEqualTo(Date value) {
            addCriterionForJDBCTime("SCDLSJ =", value, "SCDLSJ");
            return (Criteria) this;
        }

        public Criteria andSCDLSJNotEqualTo(Date value) {
            addCriterionForJDBCTime("SCDLSJ <>", value, "SCDLSJ");
            return (Criteria) this;
        }

        public Criteria andSCDLSJGreaterThan(Date value) {
            addCriterionForJDBCTime("SCDLSJ >", value, "SCDLSJ");
            return (Criteria) this;
        }

        public Criteria andSCDLSJGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("SCDLSJ >=", value, "SCDLSJ");
            return (Criteria) this;
        }

        public Criteria andSCDLSJLessThan(Date value) {
            addCriterionForJDBCTime("SCDLSJ <", value, "SCDLSJ");
            return (Criteria) this;
        }

        public Criteria andSCDLSJLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("SCDLSJ <=", value, "SCDLSJ");
            return (Criteria) this;
        }

        public Criteria andSCDLSJIn(List<Date> values) {
            addCriterionForJDBCTime("SCDLSJ in", values, "SCDLSJ");
            return (Criteria) this;
        }

        public Criteria andSCDLSJNotIn(List<Date> values) {
            addCriterionForJDBCTime("SCDLSJ not in", values, "SCDLSJ");
            return (Criteria) this;
        }

        public Criteria andSCDLSJBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("SCDLSJ between", value1, value2, "SCDLSJ");
            return (Criteria) this;
        }

        public Criteria andSCDLSJNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("SCDLSJ not between", value1, value2, "SCDLSJ");
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

        public Criteria andCJR_UIsNull() {
            addCriterion("CJR_U is null");
            return (Criteria) this;
        }

        public Criteria andCJR_UIsNotNull() {
            addCriterion("CJR_U is not null");
            return (Criteria) this;
        }

        public Criteria andCJR_UEqualTo(String value) {
            addCriterion("CJR_U =", value, "CJR_U");
            return (Criteria) this;
        }

        public Criteria andCJR_UNotEqualTo(String value) {
            addCriterion("CJR_U <>", value, "CJR_U");
            return (Criteria) this;
        }

        public Criteria andCJR_UGreaterThan(String value) {
            addCriterion("CJR_U >", value, "CJR_U");
            return (Criteria) this;
        }

        public Criteria andCJR_UGreaterThanOrEqualTo(String value) {
            addCriterion("CJR_U >=", value, "CJR_U");
            return (Criteria) this;
        }

        public Criteria andCJR_ULessThan(String value) {
            addCriterion("CJR_U <", value, "CJR_U");
            return (Criteria) this;
        }

        public Criteria andCJR_ULessThanOrEqualTo(String value) {
            addCriterion("CJR_U <=", value, "CJR_U");
            return (Criteria) this;
        }

        public Criteria andCJR_ULike(String value) {
            addCriterion("CJR_U like", value, "CJR_U");
            return (Criteria) this;
        }

        public Criteria andCJR_UNotLike(String value) {
            addCriterion("CJR_U not like", value, "CJR_U");
            return (Criteria) this;
        }

        public Criteria andCJR_UIn(List<String> values) {
            addCriterion("CJR_U in", values, "CJR_U");
            return (Criteria) this;
        }

        public Criteria andCJR_UNotIn(List<String> values) {
            addCriterion("CJR_U not in", values, "CJR_U");
            return (Criteria) this;
        }

        public Criteria andCJR_UBetween(String value1, String value2) {
            addCriterion("CJR_U between", value1, value2, "CJR_U");
            return (Criteria) this;
        }

        public Criteria andCJR_UNotBetween(String value1, String value2) {
            addCriterion("CJR_U not between", value1, value2, "CJR_U");
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