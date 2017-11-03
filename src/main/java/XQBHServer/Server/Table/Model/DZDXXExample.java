package XQBHServer.Server.Table.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DZDXXExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DZDXXExample() {
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

        public Criteria andZDLXBHIsNull() {
            addCriterion("ZDLXBH is null");
            return (Criteria) this;
        }

        public Criteria andZDLXBHIsNotNull() {
            addCriterion("ZDLXBH is not null");
            return (Criteria) this;
        }

        public Criteria andZDLXBHEqualTo(String value) {
            addCriterion("ZDLXBH =", value, "ZDLXBH");
            return (Criteria) this;
        }

        public Criteria andZDLXBHNotEqualTo(String value) {
            addCriterion("ZDLXBH <>", value, "ZDLXBH");
            return (Criteria) this;
        }

        public Criteria andZDLXBHGreaterThan(String value) {
            addCriterion("ZDLXBH >", value, "ZDLXBH");
            return (Criteria) this;
        }

        public Criteria andZDLXBHGreaterThanOrEqualTo(String value) {
            addCriterion("ZDLXBH >=", value, "ZDLXBH");
            return (Criteria) this;
        }

        public Criteria andZDLXBHLessThan(String value) {
            addCriterion("ZDLXBH <", value, "ZDLXBH");
            return (Criteria) this;
        }

        public Criteria andZDLXBHLessThanOrEqualTo(String value) {
            addCriterion("ZDLXBH <=", value, "ZDLXBH");
            return (Criteria) this;
        }

        public Criteria andZDLXBHLike(String value) {
            addCriterion("ZDLXBH like", value, "ZDLXBH");
            return (Criteria) this;
        }

        public Criteria andZDLXBHNotLike(String value) {
            addCriterion("ZDLXBH not like", value, "ZDLXBH");
            return (Criteria) this;
        }

        public Criteria andZDLXBHIn(List<String> values) {
            addCriterion("ZDLXBH in", values, "ZDLXBH");
            return (Criteria) this;
        }

        public Criteria andZDLXBHNotIn(List<String> values) {
            addCriterion("ZDLXBH not in", values, "ZDLXBH");
            return (Criteria) this;
        }

        public Criteria andZDLXBHBetween(String value1, String value2) {
            addCriterion("ZDLXBH between", value1, value2, "ZDLXBH");
            return (Criteria) this;
        }

        public Criteria andZDLXBHNotBetween(String value1, String value2) {
            addCriterion("ZDLXBH not between", value1, value2, "ZDLXBH");
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

        public Criteria andZDZT_UIsNull() {
            addCriterion("ZDZT_U is null");
            return (Criteria) this;
        }

        public Criteria andZDZT_UIsNotNull() {
            addCriterion("ZDZT_U is not null");
            return (Criteria) this;
        }

        public Criteria andZDZT_UEqualTo(String value) {
            addCriterion("ZDZT_U =", value, "ZDZT_U");
            return (Criteria) this;
        }

        public Criteria andZDZT_UNotEqualTo(String value) {
            addCriterion("ZDZT_U <>", value, "ZDZT_U");
            return (Criteria) this;
        }

        public Criteria andZDZT_UGreaterThan(String value) {
            addCriterion("ZDZT_U >", value, "ZDZT_U");
            return (Criteria) this;
        }

        public Criteria andZDZT_UGreaterThanOrEqualTo(String value) {
            addCriterion("ZDZT_U >=", value, "ZDZT_U");
            return (Criteria) this;
        }

        public Criteria andZDZT_ULessThan(String value) {
            addCriterion("ZDZT_U <", value, "ZDZT_U");
            return (Criteria) this;
        }

        public Criteria andZDZT_ULessThanOrEqualTo(String value) {
            addCriterion("ZDZT_U <=", value, "ZDZT_U");
            return (Criteria) this;
        }

        public Criteria andZDZT_ULike(String value) {
            addCriterion("ZDZT_U like", value, "ZDZT_U");
            return (Criteria) this;
        }

        public Criteria andZDZT_UNotLike(String value) {
            addCriterion("ZDZT_U not like", value, "ZDZT_U");
            return (Criteria) this;
        }

        public Criteria andZDZT_UIn(List<String> values) {
            addCriterion("ZDZT_U in", values, "ZDZT_U");
            return (Criteria) this;
        }

        public Criteria andZDZT_UNotIn(List<String> values) {
            addCriterion("ZDZT_U not in", values, "ZDZT_U");
            return (Criteria) this;
        }

        public Criteria andZDZT_UBetween(String value1, String value2) {
            addCriterion("ZDZT_U between", value1, value2, "ZDZT_U");
            return (Criteria) this;
        }

        public Criteria andZDZT_UNotBetween(String value1, String value2) {
            addCriterion("ZDZT_U not between", value1, value2, "ZDZT_U");
            return (Criteria) this;
        }

        public Criteria andXCSFRQIsNull() {
            addCriterion("XCSFRQ is null");
            return (Criteria) this;
        }

        public Criteria andXCSFRQIsNotNull() {
            addCriterion("XCSFRQ is not null");
            return (Criteria) this;
        }

        public Criteria andXCSFRQEqualTo(Date value) {
            addCriterionForJDBCDate("XCSFRQ =", value, "XCSFRQ");
            return (Criteria) this;
        }

        public Criteria andXCSFRQNotEqualTo(Date value) {
            addCriterionForJDBCDate("XCSFRQ <>", value, "XCSFRQ");
            return (Criteria) this;
        }

        public Criteria andXCSFRQGreaterThan(Date value) {
            addCriterionForJDBCDate("XCSFRQ >", value, "XCSFRQ");
            return (Criteria) this;
        }

        public Criteria andXCSFRQGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("XCSFRQ >=", value, "XCSFRQ");
            return (Criteria) this;
        }

        public Criteria andXCSFRQLessThan(Date value) {
            addCriterionForJDBCDate("XCSFRQ <", value, "XCSFRQ");
            return (Criteria) this;
        }

        public Criteria andXCSFRQLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("XCSFRQ <=", value, "XCSFRQ");
            return (Criteria) this;
        }

        public Criteria andXCSFRQIn(List<Date> values) {
            addCriterionForJDBCDate("XCSFRQ in", values, "XCSFRQ");
            return (Criteria) this;
        }

        public Criteria andXCSFRQNotIn(List<Date> values) {
            addCriterionForJDBCDate("XCSFRQ not in", values, "XCSFRQ");
            return (Criteria) this;
        }

        public Criteria andXCSFRQBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("XCSFRQ between", value1, value2, "XCSFRQ");
            return (Criteria) this;
        }

        public Criteria andXCSFRQNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("XCSFRQ not between", value1, value2, "XCSFRQ");
            return (Criteria) this;
        }

        public Criteria andZDJYM_IsNull() {
            addCriterion("ZDJYM_ is null");
            return (Criteria) this;
        }

        public Criteria andZDJYM_IsNotNull() {
            addCriterion("ZDJYM_ is not null");
            return (Criteria) this;
        }

        public Criteria andZDJYM_EqualTo(String value) {
            addCriterion("ZDJYM_ =", value, "ZDJYM_");
            return (Criteria) this;
        }

        public Criteria andZDJYM_NotEqualTo(String value) {
            addCriterion("ZDJYM_ <>", value, "ZDJYM_");
            return (Criteria) this;
        }

        public Criteria andZDJYM_GreaterThan(String value) {
            addCriterion("ZDJYM_ >", value, "ZDJYM_");
            return (Criteria) this;
        }

        public Criteria andZDJYM_GreaterThanOrEqualTo(String value) {
            addCriterion("ZDJYM_ >=", value, "ZDJYM_");
            return (Criteria) this;
        }

        public Criteria andZDJYM_LessThan(String value) {
            addCriterion("ZDJYM_ <", value, "ZDJYM_");
            return (Criteria) this;
        }

        public Criteria andZDJYM_LessThanOrEqualTo(String value) {
            addCriterion("ZDJYM_ <=", value, "ZDJYM_");
            return (Criteria) this;
        }

        public Criteria andZDJYM_Like(String value) {
            addCriterion("ZDJYM_ like", value, "ZDJYM_");
            return (Criteria) this;
        }

        public Criteria andZDJYM_NotLike(String value) {
            addCriterion("ZDJYM_ not like", value, "ZDJYM_");
            return (Criteria) this;
        }

        public Criteria andZDJYM_In(List<String> values) {
            addCriterion("ZDJYM_ in", values, "ZDJYM_");
            return (Criteria) this;
        }

        public Criteria andZDJYM_NotIn(List<String> values) {
            addCriterion("ZDJYM_ not in", values, "ZDJYM_");
            return (Criteria) this;
        }

        public Criteria andZDJYM_Between(String value1, String value2) {
            addCriterion("ZDJYM_ between", value1, value2, "ZDJYM_");
            return (Criteria) this;
        }

        public Criteria andZDJYM_NotBetween(String value1, String value2) {
            addCriterion("ZDJYM_ not between", value1, value2, "ZDJYM_");
            return (Criteria) this;
        }

        public Criteria andZDDLZTIsNull() {
            addCriterion("ZDDLZT is null");
            return (Criteria) this;
        }

        public Criteria andZDDLZTIsNotNull() {
            addCriterion("ZDDLZT is not null");
            return (Criteria) this;
        }

        public Criteria andZDDLZTEqualTo(String value) {
            addCriterion("ZDDLZT =", value, "ZDDLZT");
            return (Criteria) this;
        }

        public Criteria andZDDLZTNotEqualTo(String value) {
            addCriterion("ZDDLZT <>", value, "ZDDLZT");
            return (Criteria) this;
        }

        public Criteria andZDDLZTGreaterThan(String value) {
            addCriterion("ZDDLZT >", value, "ZDDLZT");
            return (Criteria) this;
        }

        public Criteria andZDDLZTGreaterThanOrEqualTo(String value) {
            addCriterion("ZDDLZT >=", value, "ZDDLZT");
            return (Criteria) this;
        }

        public Criteria andZDDLZTLessThan(String value) {
            addCriterion("ZDDLZT <", value, "ZDDLZT");
            return (Criteria) this;
        }

        public Criteria andZDDLZTLessThanOrEqualTo(String value) {
            addCriterion("ZDDLZT <=", value, "ZDDLZT");
            return (Criteria) this;
        }

        public Criteria andZDDLZTLike(String value) {
            addCriterion("ZDDLZT like", value, "ZDDLZT");
            return (Criteria) this;
        }

        public Criteria andZDDLZTNotLike(String value) {
            addCriterion("ZDDLZT not like", value, "ZDDLZT");
            return (Criteria) this;
        }

        public Criteria andZDDLZTIn(List<String> values) {
            addCriterion("ZDDLZT in", values, "ZDDLZT");
            return (Criteria) this;
        }

        public Criteria andZDDLZTNotIn(List<String> values) {
            addCriterion("ZDDLZT not in", values, "ZDDLZT");
            return (Criteria) this;
        }

        public Criteria andZDDLZTBetween(String value1, String value2) {
            addCriterion("ZDDLZT between", value1, value2, "ZDDLZT");
            return (Criteria) this;
        }

        public Criteria andZDDLZTNotBetween(String value1, String value2) {
            addCriterion("ZDDLZT not between", value1, value2, "ZDDLZT");
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

        public Criteria andLJXSGSIsNull() {
            addCriterion("LJXSGS is null");
            return (Criteria) this;
        }

        public Criteria andLJXSGSIsNotNull() {
            addCriterion("LJXSGS is not null");
            return (Criteria) this;
        }

        public Criteria andLJXSGSEqualTo(Integer value) {
            addCriterion("LJXSGS =", value, "LJXSGS");
            return (Criteria) this;
        }

        public Criteria andLJXSGSNotEqualTo(Integer value) {
            addCriterion("LJXSGS <>", value, "LJXSGS");
            return (Criteria) this;
        }

        public Criteria andLJXSGSGreaterThan(Integer value) {
            addCriterion("LJXSGS >", value, "LJXSGS");
            return (Criteria) this;
        }

        public Criteria andLJXSGSGreaterThanOrEqualTo(Integer value) {
            addCriterion("LJXSGS >=", value, "LJXSGS");
            return (Criteria) this;
        }

        public Criteria andLJXSGSLessThan(Integer value) {
            addCriterion("LJXSGS <", value, "LJXSGS");
            return (Criteria) this;
        }

        public Criteria andLJXSGSLessThanOrEqualTo(Integer value) {
            addCriterion("LJXSGS <=", value, "LJXSGS");
            return (Criteria) this;
        }

        public Criteria andLJXSGSIn(List<Integer> values) {
            addCriterion("LJXSGS in", values, "LJXSGS");
            return (Criteria) this;
        }

        public Criteria andLJXSGSNotIn(List<Integer> values) {
            addCriterion("LJXSGS not in", values, "LJXSGS");
            return (Criteria) this;
        }

        public Criteria andLJXSGSBetween(Integer value1, Integer value2) {
            addCriterion("LJXSGS between", value1, value2, "LJXSGS");
            return (Criteria) this;
        }

        public Criteria andLJXSGSNotBetween(Integer value1, Integer value2) {
            addCriterion("LJXSGS not between", value1, value2, "LJXSGS");
            return (Criteria) this;
        }

        public Criteria andLJXSE_IsNull() {
            addCriterion("LJXSE_ is null");
            return (Criteria) this;
        }

        public Criteria andLJXSE_IsNotNull() {
            addCriterion("LJXSE_ is not null");
            return (Criteria) this;
        }

        public Criteria andLJXSE_EqualTo(Long value) {
            addCriterion("LJXSE_ =", value, "LJXSE_");
            return (Criteria) this;
        }

        public Criteria andLJXSE_NotEqualTo(Long value) {
            addCriterion("LJXSE_ <>", value, "LJXSE_");
            return (Criteria) this;
        }

        public Criteria andLJXSE_GreaterThan(Long value) {
            addCriterion("LJXSE_ >", value, "LJXSE_");
            return (Criteria) this;
        }

        public Criteria andLJXSE_GreaterThanOrEqualTo(Long value) {
            addCriterion("LJXSE_ >=", value, "LJXSE_");
            return (Criteria) this;
        }

        public Criteria andLJXSE_LessThan(Long value) {
            addCriterion("LJXSE_ <", value, "LJXSE_");
            return (Criteria) this;
        }

        public Criteria andLJXSE_LessThanOrEqualTo(Long value) {
            addCriterion("LJXSE_ <=", value, "LJXSE_");
            return (Criteria) this;
        }

        public Criteria andLJXSE_In(List<Long> values) {
            addCriterion("LJXSE_ in", values, "LJXSE_");
            return (Criteria) this;
        }

        public Criteria andLJXSE_NotIn(List<Long> values) {
            addCriterion("LJXSE_ not in", values, "LJXSE_");
            return (Criteria) this;
        }

        public Criteria andLJXSE_Between(Long value1, Long value2) {
            addCriterion("LJXSE_ between", value1, value2, "LJXSE_");
            return (Criteria) this;
        }

        public Criteria andLJXSE_NotBetween(Long value1, Long value2) {
            addCriterion("LJXSE_ not between", value1, value2, "LJXSE_");
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