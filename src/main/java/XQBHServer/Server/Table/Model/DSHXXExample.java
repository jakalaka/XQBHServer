package XQBHServer.Server.Table.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DSHXXExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DSHXXExample() {
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

        public Criteria andSHMC_UIsNull() {
            addCriterion("SHMC_U is null");
            return (Criteria) this;
        }

        public Criteria andSHMC_UIsNotNull() {
            addCriterion("SHMC_U is not null");
            return (Criteria) this;
        }

        public Criteria andSHMC_UEqualTo(String value) {
            addCriterion("SHMC_U =", value, "SHMC_U");
            return (Criteria) this;
        }

        public Criteria andSHMC_UNotEqualTo(String value) {
            addCriterion("SHMC_U <>", value, "SHMC_U");
            return (Criteria) this;
        }

        public Criteria andSHMC_UGreaterThan(String value) {
            addCriterion("SHMC_U >", value, "SHMC_U");
            return (Criteria) this;
        }

        public Criteria andSHMC_UGreaterThanOrEqualTo(String value) {
            addCriterion("SHMC_U >=", value, "SHMC_U");
            return (Criteria) this;
        }

        public Criteria andSHMC_ULessThan(String value) {
            addCriterion("SHMC_U <", value, "SHMC_U");
            return (Criteria) this;
        }

        public Criteria andSHMC_ULessThanOrEqualTo(String value) {
            addCriterion("SHMC_U <=", value, "SHMC_U");
            return (Criteria) this;
        }

        public Criteria andSHMC_ULike(String value) {
            addCriterion("SHMC_U like", value, "SHMC_U");
            return (Criteria) this;
        }

        public Criteria andSHMC_UNotLike(String value) {
            addCriterion("SHMC_U not like", value, "SHMC_U");
            return (Criteria) this;
        }

        public Criteria andSHMC_UIn(List<String> values) {
            addCriterion("SHMC_U in", values, "SHMC_U");
            return (Criteria) this;
        }

        public Criteria andSHMC_UNotIn(List<String> values) {
            addCriterion("SHMC_U not in", values, "SHMC_U");
            return (Criteria) this;
        }

        public Criteria andSHMC_UBetween(String value1, String value2) {
            addCriterion("SHMC_U between", value1, value2, "SHMC_U");
            return (Criteria) this;
        }

        public Criteria andSHMC_UNotBetween(String value1, String value2) {
            addCriterion("SHMC_U not between", value1, value2, "SHMC_U");
            return (Criteria) this;
        }

        public Criteria andYB_UUUIsNull() {
            addCriterion("YB_UUU is null");
            return (Criteria) this;
        }

        public Criteria andYB_UUUIsNotNull() {
            addCriterion("YB_UUU is not null");
            return (Criteria) this;
        }

        public Criteria andYB_UUUEqualTo(String value) {
            addCriterion("YB_UUU =", value, "YB_UUU");
            return (Criteria) this;
        }

        public Criteria andYB_UUUNotEqualTo(String value) {
            addCriterion("YB_UUU <>", value, "YB_UUU");
            return (Criteria) this;
        }

        public Criteria andYB_UUUGreaterThan(String value) {
            addCriterion("YB_UUU >", value, "YB_UUU");
            return (Criteria) this;
        }

        public Criteria andYB_UUUGreaterThanOrEqualTo(String value) {
            addCriterion("YB_UUU >=", value, "YB_UUU");
            return (Criteria) this;
        }

        public Criteria andYB_UUULessThan(String value) {
            addCriterion("YB_UUU <", value, "YB_UUU");
            return (Criteria) this;
        }

        public Criteria andYB_UUULessThanOrEqualTo(String value) {
            addCriterion("YB_UUU <=", value, "YB_UUU");
            return (Criteria) this;
        }

        public Criteria andYB_UUULike(String value) {
            addCriterion("YB_UUU like", value, "YB_UUU");
            return (Criteria) this;
        }

        public Criteria andYB_UUUNotLike(String value) {
            addCriterion("YB_UUU not like", value, "YB_UUU");
            return (Criteria) this;
        }

        public Criteria andYB_UUUIn(List<String> values) {
            addCriterion("YB_UUU in", values, "YB_UUU");
            return (Criteria) this;
        }

        public Criteria andYB_UUUNotIn(List<String> values) {
            addCriterion("YB_UUU not in", values, "YB_UUU");
            return (Criteria) this;
        }

        public Criteria andYB_UUUBetween(String value1, String value2) {
            addCriterion("YB_UUU between", value1, value2, "YB_UUU");
            return (Criteria) this;
        }

        public Criteria andYB_UUUNotBetween(String value1, String value2) {
            addCriterion("YB_UUU not between", value1, value2, "YB_UUU");
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

        public Criteria andSHLXBHIsNull() {
            addCriterion("SHLXBH is null");
            return (Criteria) this;
        }

        public Criteria andSHLXBHIsNotNull() {
            addCriterion("SHLXBH is not null");
            return (Criteria) this;
        }

        public Criteria andSHLXBHEqualTo(String value) {
            addCriterion("SHLXBH =", value, "SHLXBH");
            return (Criteria) this;
        }

        public Criteria andSHLXBHNotEqualTo(String value) {
            addCriterion("SHLXBH <>", value, "SHLXBH");
            return (Criteria) this;
        }

        public Criteria andSHLXBHGreaterThan(String value) {
            addCriterion("SHLXBH >", value, "SHLXBH");
            return (Criteria) this;
        }

        public Criteria andSHLXBHGreaterThanOrEqualTo(String value) {
            addCriterion("SHLXBH >=", value, "SHLXBH");
            return (Criteria) this;
        }

        public Criteria andSHLXBHLessThan(String value) {
            addCriterion("SHLXBH <", value, "SHLXBH");
            return (Criteria) this;
        }

        public Criteria andSHLXBHLessThanOrEqualTo(String value) {
            addCriterion("SHLXBH <=", value, "SHLXBH");
            return (Criteria) this;
        }

        public Criteria andSHLXBHLike(String value) {
            addCriterion("SHLXBH like", value, "SHLXBH");
            return (Criteria) this;
        }

        public Criteria andSHLXBHNotLike(String value) {
            addCriterion("SHLXBH not like", value, "SHLXBH");
            return (Criteria) this;
        }

        public Criteria andSHLXBHIn(List<String> values) {
            addCriterion("SHLXBH in", values, "SHLXBH");
            return (Criteria) this;
        }

        public Criteria andSHLXBHNotIn(List<String> values) {
            addCriterion("SHLXBH not in", values, "SHLXBH");
            return (Criteria) this;
        }

        public Criteria andSHLXBHBetween(String value1, String value2) {
            addCriterion("SHLXBH between", value1, value2, "SHLXBH");
            return (Criteria) this;
        }

        public Criteria andSHLXBHNotBetween(String value1, String value2) {
            addCriterion("SHLXBH not between", value1, value2, "SHLXBH");
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

        public Criteria andZZJGDMIsNull() {
            addCriterion("ZZJGDM is null");
            return (Criteria) this;
        }

        public Criteria andZZJGDMIsNotNull() {
            addCriterion("ZZJGDM is not null");
            return (Criteria) this;
        }

        public Criteria andZZJGDMEqualTo(String value) {
            addCriterion("ZZJGDM =", value, "ZZJGDM");
            return (Criteria) this;
        }

        public Criteria andZZJGDMNotEqualTo(String value) {
            addCriterion("ZZJGDM <>", value, "ZZJGDM");
            return (Criteria) this;
        }

        public Criteria andZZJGDMGreaterThan(String value) {
            addCriterion("ZZJGDM >", value, "ZZJGDM");
            return (Criteria) this;
        }

        public Criteria andZZJGDMGreaterThanOrEqualTo(String value) {
            addCriterion("ZZJGDM >=", value, "ZZJGDM");
            return (Criteria) this;
        }

        public Criteria andZZJGDMLessThan(String value) {
            addCriterion("ZZJGDM <", value, "ZZJGDM");
            return (Criteria) this;
        }

        public Criteria andZZJGDMLessThanOrEqualTo(String value) {
            addCriterion("ZZJGDM <=", value, "ZZJGDM");
            return (Criteria) this;
        }

        public Criteria andZZJGDMLike(String value) {
            addCriterion("ZZJGDM like", value, "ZZJGDM");
            return (Criteria) this;
        }

        public Criteria andZZJGDMNotLike(String value) {
            addCriterion("ZZJGDM not like", value, "ZZJGDM");
            return (Criteria) this;
        }

        public Criteria andZZJGDMIn(List<String> values) {
            addCriterion("ZZJGDM in", values, "ZZJGDM");
            return (Criteria) this;
        }

        public Criteria andZZJGDMNotIn(List<String> values) {
            addCriterion("ZZJGDM not in", values, "ZZJGDM");
            return (Criteria) this;
        }

        public Criteria andZZJGDMBetween(String value1, String value2) {
            addCriterion("ZZJGDM between", value1, value2, "ZZJGDM");
            return (Criteria) this;
        }

        public Criteria andZZJGDMNotBetween(String value1, String value2) {
            addCriterion("ZZJGDM not between", value1, value2, "ZZJGDM");
            return (Criteria) this;
        }

        public Criteria andGLRXM_IsNull() {
            addCriterion("GLRXM_ is null");
            return (Criteria) this;
        }

        public Criteria andGLRXM_IsNotNull() {
            addCriterion("GLRXM_ is not null");
            return (Criteria) this;
        }

        public Criteria andGLRXM_EqualTo(String value) {
            addCriterion("GLRXM_ =", value, "GLRXM_");
            return (Criteria) this;
        }

        public Criteria andGLRXM_NotEqualTo(String value) {
            addCriterion("GLRXM_ <>", value, "GLRXM_");
            return (Criteria) this;
        }

        public Criteria andGLRXM_GreaterThan(String value) {
            addCriterion("GLRXM_ >", value, "GLRXM_");
            return (Criteria) this;
        }

        public Criteria andGLRXM_GreaterThanOrEqualTo(String value) {
            addCriterion("GLRXM_ >=", value, "GLRXM_");
            return (Criteria) this;
        }

        public Criteria andGLRXM_LessThan(String value) {
            addCriterion("GLRXM_ <", value, "GLRXM_");
            return (Criteria) this;
        }

        public Criteria andGLRXM_LessThanOrEqualTo(String value) {
            addCriterion("GLRXM_ <=", value, "GLRXM_");
            return (Criteria) this;
        }

        public Criteria andGLRXM_Like(String value) {
            addCriterion("GLRXM_ like", value, "GLRXM_");
            return (Criteria) this;
        }

        public Criteria andGLRXM_NotLike(String value) {
            addCriterion("GLRXM_ not like", value, "GLRXM_");
            return (Criteria) this;
        }

        public Criteria andGLRXM_In(List<String> values) {
            addCriterion("GLRXM_ in", values, "GLRXM_");
            return (Criteria) this;
        }

        public Criteria andGLRXM_NotIn(List<String> values) {
            addCriterion("GLRXM_ not in", values, "GLRXM_");
            return (Criteria) this;
        }

        public Criteria andGLRXM_Between(String value1, String value2) {
            addCriterion("GLRXM_ between", value1, value2, "GLRXM_");
            return (Criteria) this;
        }

        public Criteria andGLRXM_NotBetween(String value1, String value2) {
            addCriterion("GLRXM_ not between", value1, value2, "GLRXM_");
            return (Criteria) this;
        }

        public Criteria andGLRDH_IsNull() {
            addCriterion("GLRDH_ is null");
            return (Criteria) this;
        }

        public Criteria andGLRDH_IsNotNull() {
            addCriterion("GLRDH_ is not null");
            return (Criteria) this;
        }

        public Criteria andGLRDH_EqualTo(String value) {
            addCriterion("GLRDH_ =", value, "GLRDH_");
            return (Criteria) this;
        }

        public Criteria andGLRDH_NotEqualTo(String value) {
            addCriterion("GLRDH_ <>", value, "GLRDH_");
            return (Criteria) this;
        }

        public Criteria andGLRDH_GreaterThan(String value) {
            addCriterion("GLRDH_ >", value, "GLRDH_");
            return (Criteria) this;
        }

        public Criteria andGLRDH_GreaterThanOrEqualTo(String value) {
            addCriterion("GLRDH_ >=", value, "GLRDH_");
            return (Criteria) this;
        }

        public Criteria andGLRDH_LessThan(String value) {
            addCriterion("GLRDH_ <", value, "GLRDH_");
            return (Criteria) this;
        }

        public Criteria andGLRDH_LessThanOrEqualTo(String value) {
            addCriterion("GLRDH_ <=", value, "GLRDH_");
            return (Criteria) this;
        }

        public Criteria andGLRDH_Like(String value) {
            addCriterion("GLRDH_ like", value, "GLRDH_");
            return (Criteria) this;
        }

        public Criteria andGLRDH_NotLike(String value) {
            addCriterion("GLRDH_ not like", value, "GLRDH_");
            return (Criteria) this;
        }

        public Criteria andGLRDH_In(List<String> values) {
            addCriterion("GLRDH_ in", values, "GLRDH_");
            return (Criteria) this;
        }

        public Criteria andGLRDH_NotIn(List<String> values) {
            addCriterion("GLRDH_ not in", values, "GLRDH_");
            return (Criteria) this;
        }

        public Criteria andGLRDH_Between(String value1, String value2) {
            addCriterion("GLRDH_ between", value1, value2, "GLRDH_");
            return (Criteria) this;
        }

        public Criteria andGLRDH_NotBetween(String value1, String value2) {
            addCriterion("GLRDH_ not between", value1, value2, "GLRDH_");
            return (Criteria) this;
        }

        public Criteria andDQYE_UIsNull() {
            addCriterion("DQYE_U is null");
            return (Criteria) this;
        }

        public Criteria andDQYE_UIsNotNull() {
            addCriterion("DQYE_U is not null");
            return (Criteria) this;
        }

        public Criteria andDQYE_UEqualTo(BigDecimal value) {
            addCriterion("DQYE_U =", value, "DQYE_U");
            return (Criteria) this;
        }

        public Criteria andDQYE_UNotEqualTo(BigDecimal value) {
            addCriterion("DQYE_U <>", value, "DQYE_U");
            return (Criteria) this;
        }

        public Criteria andDQYE_UGreaterThan(BigDecimal value) {
            addCriterion("DQYE_U >", value, "DQYE_U");
            return (Criteria) this;
        }

        public Criteria andDQYE_UGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DQYE_U >=", value, "DQYE_U");
            return (Criteria) this;
        }

        public Criteria andDQYE_ULessThan(BigDecimal value) {
            addCriterion("DQYE_U <", value, "DQYE_U");
            return (Criteria) this;
        }

        public Criteria andDQYE_ULessThanOrEqualTo(BigDecimal value) {
            addCriterion("DQYE_U <=", value, "DQYE_U");
            return (Criteria) this;
        }

        public Criteria andDQYE_UIn(List<BigDecimal> values) {
            addCriterion("DQYE_U in", values, "DQYE_U");
            return (Criteria) this;
        }

        public Criteria andDQYE_UNotIn(List<BigDecimal> values) {
            addCriterion("DQYE_U not in", values, "DQYE_U");
            return (Criteria) this;
        }

        public Criteria andDQYE_UBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DQYE_U between", value1, value2, "DQYE_U");
            return (Criteria) this;
        }

        public Criteria andDQYE_UNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DQYE_U not between", value1, value2, "DQYE_U");
            return (Criteria) this;
        }

        public Criteria andSHMM_UIsNull() {
            addCriterion("SHMM_U is null");
            return (Criteria) this;
        }

        public Criteria andSHMM_UIsNotNull() {
            addCriterion("SHMM_U is not null");
            return (Criteria) this;
        }

        public Criteria andSHMM_UEqualTo(String value) {
            addCriterion("SHMM_U =", value, "SHMM_U");
            return (Criteria) this;
        }

        public Criteria andSHMM_UNotEqualTo(String value) {
            addCriterion("SHMM_U <>", value, "SHMM_U");
            return (Criteria) this;
        }

        public Criteria andSHMM_UGreaterThan(String value) {
            addCriterion("SHMM_U >", value, "SHMM_U");
            return (Criteria) this;
        }

        public Criteria andSHMM_UGreaterThanOrEqualTo(String value) {
            addCriterion("SHMM_U >=", value, "SHMM_U");
            return (Criteria) this;
        }

        public Criteria andSHMM_ULessThan(String value) {
            addCriterion("SHMM_U <", value, "SHMM_U");
            return (Criteria) this;
        }

        public Criteria andSHMM_ULessThanOrEqualTo(String value) {
            addCriterion("SHMM_U <=", value, "SHMM_U");
            return (Criteria) this;
        }

        public Criteria andSHMM_ULike(String value) {
            addCriterion("SHMM_U like", value, "SHMM_U");
            return (Criteria) this;
        }

        public Criteria andSHMM_UNotLike(String value) {
            addCriterion("SHMM_U not like", value, "SHMM_U");
            return (Criteria) this;
        }

        public Criteria andSHMM_UIn(List<String> values) {
            addCriterion("SHMM_U in", values, "SHMM_U");
            return (Criteria) this;
        }

        public Criteria andSHMM_UNotIn(List<String> values) {
            addCriterion("SHMM_U not in", values, "SHMM_U");
            return (Criteria) this;
        }

        public Criteria andSHMM_UBetween(String value1, String value2) {
            addCriterion("SHMM_U between", value1, value2, "SHMM_U");
            return (Criteria) this;
        }

        public Criteria andSHMM_UNotBetween(String value1, String value2) {
            addCriterion("SHMM_U not between", value1, value2, "SHMM_U");
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

        public Criteria andSCJYRQIsNull() {
            addCriterion("SCJYRQ is null");
            return (Criteria) this;
        }

        public Criteria andSCJYRQIsNotNull() {
            addCriterion("SCJYRQ is not null");
            return (Criteria) this;
        }

        public Criteria andSCJYRQEqualTo(Date value) {
            addCriterionForJDBCDate("SCJYRQ =", value, "SCJYRQ");
            return (Criteria) this;
        }

        public Criteria andSCJYRQNotEqualTo(Date value) {
            addCriterionForJDBCDate("SCJYRQ <>", value, "SCJYRQ");
            return (Criteria) this;
        }

        public Criteria andSCJYRQGreaterThan(Date value) {
            addCriterionForJDBCDate("SCJYRQ >", value, "SCJYRQ");
            return (Criteria) this;
        }

        public Criteria andSCJYRQGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SCJYRQ >=", value, "SCJYRQ");
            return (Criteria) this;
        }

        public Criteria andSCJYRQLessThan(Date value) {
            addCriterionForJDBCDate("SCJYRQ <", value, "SCJYRQ");
            return (Criteria) this;
        }

        public Criteria andSCJYRQLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SCJYRQ <=", value, "SCJYRQ");
            return (Criteria) this;
        }

        public Criteria andSCJYRQIn(List<Date> values) {
            addCriterionForJDBCDate("SCJYRQ in", values, "SCJYRQ");
            return (Criteria) this;
        }

        public Criteria andSCJYRQNotIn(List<Date> values) {
            addCriterionForJDBCDate("SCJYRQ not in", values, "SCJYRQ");
            return (Criteria) this;
        }

        public Criteria andSCJYRQBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SCJYRQ between", value1, value2, "SCJYRQ");
            return (Criteria) this;
        }

        public Criteria andSCJYRQNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SCJYRQ not between", value1, value2, "SCJYRQ");
            return (Criteria) this;
        }

        public Criteria andSCJYXHIsNull() {
            addCriterion("SCJYXH is null");
            return (Criteria) this;
        }

        public Criteria andSCJYXHIsNotNull() {
            addCriterion("SCJYXH is not null");
            return (Criteria) this;
        }

        public Criteria andSCJYXHEqualTo(Integer value) {
            addCriterion("SCJYXH =", value, "SCJYXH");
            return (Criteria) this;
        }

        public Criteria andSCJYXHNotEqualTo(Integer value) {
            addCriterion("SCJYXH <>", value, "SCJYXH");
            return (Criteria) this;
        }

        public Criteria andSCJYXHGreaterThan(Integer value) {
            addCriterion("SCJYXH >", value, "SCJYXH");
            return (Criteria) this;
        }

        public Criteria andSCJYXHGreaterThanOrEqualTo(Integer value) {
            addCriterion("SCJYXH >=", value, "SCJYXH");
            return (Criteria) this;
        }

        public Criteria andSCJYXHLessThan(Integer value) {
            addCriterion("SCJYXH <", value, "SCJYXH");
            return (Criteria) this;
        }

        public Criteria andSCJYXHLessThanOrEqualTo(Integer value) {
            addCriterion("SCJYXH <=", value, "SCJYXH");
            return (Criteria) this;
        }

        public Criteria andSCJYXHIn(List<Integer> values) {
            addCriterion("SCJYXH in", values, "SCJYXH");
            return (Criteria) this;
        }

        public Criteria andSCJYXHNotIn(List<Integer> values) {
            addCriterion("SCJYXH not in", values, "SCJYXH");
            return (Criteria) this;
        }

        public Criteria andSCJYXHBetween(Integer value1, Integer value2) {
            addCriterion("SCJYXH between", value1, value2, "SCJYXH");
            return (Criteria) this;
        }

        public Criteria andSCJYXHNotBetween(Integer value1, Integer value2) {
            addCriterion("SCJYXH not between", value1, value2, "SCJYXH");
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