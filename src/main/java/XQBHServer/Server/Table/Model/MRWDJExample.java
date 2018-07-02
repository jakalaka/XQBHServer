package XQBHServer.Server.Table.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MRWDJExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MRWDJExample() {
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

        public Criteria andRWBH_UIsNull() {
            addCriterion("RWBH_U is null");
            return (Criteria) this;
        }

        public Criteria andRWBH_UIsNotNull() {
            addCriterion("RWBH_U is not null");
            return (Criteria) this;
        }

        public Criteria andRWBH_UEqualTo(String value) {
            addCriterion("RWBH_U =", value, "RWBH_U");
            return (Criteria) this;
        }

        public Criteria andRWBH_UNotEqualTo(String value) {
            addCriterion("RWBH_U <>", value, "RWBH_U");
            return (Criteria) this;
        }

        public Criteria andRWBH_UGreaterThan(String value) {
            addCriterion("RWBH_U >", value, "RWBH_U");
            return (Criteria) this;
        }

        public Criteria andRWBH_UGreaterThanOrEqualTo(String value) {
            addCriterion("RWBH_U >=", value, "RWBH_U");
            return (Criteria) this;
        }

        public Criteria andRWBH_ULessThan(String value) {
            addCriterion("RWBH_U <", value, "RWBH_U");
            return (Criteria) this;
        }

        public Criteria andRWBH_ULessThanOrEqualTo(String value) {
            addCriterion("RWBH_U <=", value, "RWBH_U");
            return (Criteria) this;
        }

        public Criteria andRWBH_ULike(String value) {
            addCriterion("RWBH_U like", value, "RWBH_U");
            return (Criteria) this;
        }

        public Criteria andRWBH_UNotLike(String value) {
            addCriterion("RWBH_U not like", value, "RWBH_U");
            return (Criteria) this;
        }

        public Criteria andRWBH_UIn(List<String> values) {
            addCriterion("RWBH_U in", values, "RWBH_U");
            return (Criteria) this;
        }

        public Criteria andRWBH_UNotIn(List<String> values) {
            addCriterion("RWBH_U not in", values, "RWBH_U");
            return (Criteria) this;
        }

        public Criteria andRWBH_UBetween(String value1, String value2) {
            addCriterion("RWBH_U between", value1, value2, "RWBH_U");
            return (Criteria) this;
        }

        public Criteria andRWBH_UNotBetween(String value1, String value2) {
            addCriterion("RWBH_U not between", value1, value2, "RWBH_U");
            return (Criteria) this;
        }

        public Criteria andRWXH_UIsNull() {
            addCriterion("RWXH_U is null");
            return (Criteria) this;
        }

        public Criteria andRWXH_UIsNotNull() {
            addCriterion("RWXH_U is not null");
            return (Criteria) this;
        }

        public Criteria andRWXH_UEqualTo(Integer value) {
            addCriterion("RWXH_U =", value, "RWXH_U");
            return (Criteria) this;
        }

        public Criteria andRWXH_UNotEqualTo(Integer value) {
            addCriterion("RWXH_U <>", value, "RWXH_U");
            return (Criteria) this;
        }

        public Criteria andRWXH_UGreaterThan(Integer value) {
            addCriterion("RWXH_U >", value, "RWXH_U");
            return (Criteria) this;
        }

        public Criteria andRWXH_UGreaterThanOrEqualTo(Integer value) {
            addCriterion("RWXH_U >=", value, "RWXH_U");
            return (Criteria) this;
        }

        public Criteria andRWXH_ULessThan(Integer value) {
            addCriterion("RWXH_U <", value, "RWXH_U");
            return (Criteria) this;
        }

        public Criteria andRWXH_ULessThanOrEqualTo(Integer value) {
            addCriterion("RWXH_U <=", value, "RWXH_U");
            return (Criteria) this;
        }

        public Criteria andRWXH_UIn(List<Integer> values) {
            addCriterion("RWXH_U in", values, "RWXH_U");
            return (Criteria) this;
        }

        public Criteria andRWXH_UNotIn(List<Integer> values) {
            addCriterion("RWXH_U not in", values, "RWXH_U");
            return (Criteria) this;
        }

        public Criteria andRWXH_UBetween(Integer value1, Integer value2) {
            addCriterion("RWXH_U between", value1, value2, "RWXH_U");
            return (Criteria) this;
        }

        public Criteria andRWXH_UNotBetween(Integer value1, Integer value2) {
            addCriterion("RWXH_U not between", value1, value2, "RWXH_U");
            return (Criteria) this;
        }

        public Criteria andRWH_UUIsNull() {
            addCriterion("RWH_UU is null");
            return (Criteria) this;
        }

        public Criteria andRWH_UUIsNotNull() {
            addCriterion("RWH_UU is not null");
            return (Criteria) this;
        }

        public Criteria andRWH_UUEqualTo(Integer value) {
            addCriterion("RWH_UU =", value, "RWH_UU");
            return (Criteria) this;
        }

        public Criteria andRWH_UUNotEqualTo(Integer value) {
            addCriterion("RWH_UU <>", value, "RWH_UU");
            return (Criteria) this;
        }

        public Criteria andRWH_UUGreaterThan(Integer value) {
            addCriterion("RWH_UU >", value, "RWH_UU");
            return (Criteria) this;
        }

        public Criteria andRWH_UUGreaterThanOrEqualTo(Integer value) {
            addCriterion("RWH_UU >=", value, "RWH_UU");
            return (Criteria) this;
        }

        public Criteria andRWH_UULessThan(Integer value) {
            addCriterion("RWH_UU <", value, "RWH_UU");
            return (Criteria) this;
        }

        public Criteria andRWH_UULessThanOrEqualTo(Integer value) {
            addCriterion("RWH_UU <=", value, "RWH_UU");
            return (Criteria) this;
        }

        public Criteria andRWH_UUIn(List<Integer> values) {
            addCriterion("RWH_UU in", values, "RWH_UU");
            return (Criteria) this;
        }

        public Criteria andRWH_UUNotIn(List<Integer> values) {
            addCriterion("RWH_UU not in", values, "RWH_UU");
            return (Criteria) this;
        }

        public Criteria andRWH_UUBetween(Integer value1, Integer value2) {
            addCriterion("RWH_UU between", value1, value2, "RWH_UU");
            return (Criteria) this;
        }

        public Criteria andRWH_UUNotBetween(Integer value1, Integer value2) {
            addCriterion("RWH_UU not between", value1, value2, "RWH_UU");
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

        public Criteria andRWZT_UIsNull() {
            addCriterion("RWZT_U is null");
            return (Criteria) this;
        }

        public Criteria andRWZT_UIsNotNull() {
            addCriterion("RWZT_U is not null");
            return (Criteria) this;
        }

        public Criteria andRWZT_UEqualTo(String value) {
            addCriterion("RWZT_U =", value, "RWZT_U");
            return (Criteria) this;
        }

        public Criteria andRWZT_UNotEqualTo(String value) {
            addCriterion("RWZT_U <>", value, "RWZT_U");
            return (Criteria) this;
        }

        public Criteria andRWZT_UGreaterThan(String value) {
            addCriterion("RWZT_U >", value, "RWZT_U");
            return (Criteria) this;
        }

        public Criteria andRWZT_UGreaterThanOrEqualTo(String value) {
            addCriterion("RWZT_U >=", value, "RWZT_U");
            return (Criteria) this;
        }

        public Criteria andRWZT_ULessThan(String value) {
            addCriterion("RWZT_U <", value, "RWZT_U");
            return (Criteria) this;
        }

        public Criteria andRWZT_ULessThanOrEqualTo(String value) {
            addCriterion("RWZT_U <=", value, "RWZT_U");
            return (Criteria) this;
        }

        public Criteria andRWZT_ULike(String value) {
            addCriterion("RWZT_U like", value, "RWZT_U");
            return (Criteria) this;
        }

        public Criteria andRWZT_UNotLike(String value) {
            addCriterion("RWZT_U not like", value, "RWZT_U");
            return (Criteria) this;
        }

        public Criteria andRWZT_UIn(List<String> values) {
            addCriterion("RWZT_U in", values, "RWZT_U");
            return (Criteria) this;
        }

        public Criteria andRWZT_UNotIn(List<String> values) {
            addCriterion("RWZT_U not in", values, "RWZT_U");
            return (Criteria) this;
        }

        public Criteria andRWZT_UBetween(String value1, String value2) {
            addCriterion("RWZT_U between", value1, value2, "RWZT_U");
            return (Criteria) this;
        }

        public Criteria andRWZT_UNotBetween(String value1, String value2) {
            addCriterion("RWZT_U not between", value1, value2, "RWZT_U");
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