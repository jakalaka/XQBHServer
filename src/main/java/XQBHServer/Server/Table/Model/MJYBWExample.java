package XQBHServer.Server.Table.Model;

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

        public Criteria andJYRQ_UIsNull() {
            addCriterion("JYRQ_U is null");
            return (Criteria) this;
        }

        public Criteria andJYRQ_UIsNotNull() {
            addCriterion("JYRQ_U is not null");
            return (Criteria) this;
        }

        public Criteria andJYRQ_UEqualTo(Date value) {
            addCriterionForJDBCDate("JYRQ_U =", value, "JYRQ_U");
            return (Criteria) this;
        }

        public Criteria andJYRQ_UNotEqualTo(Date value) {
            addCriterionForJDBCDate("JYRQ_U <>", value, "JYRQ_U");
            return (Criteria) this;
        }

        public Criteria andJYRQ_UGreaterThan(Date value) {
            addCriterionForJDBCDate("JYRQ_U >", value, "JYRQ_U");
            return (Criteria) this;
        }

        public Criteria andJYRQ_UGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("JYRQ_U >=", value, "JYRQ_U");
            return (Criteria) this;
        }

        public Criteria andJYRQ_ULessThan(Date value) {
            addCriterionForJDBCDate("JYRQ_U <", value, "JYRQ_U");
            return (Criteria) this;
        }

        public Criteria andJYRQ_ULessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("JYRQ_U <=", value, "JYRQ_U");
            return (Criteria) this;
        }

        public Criteria andJYRQ_UIn(List<Date> values) {
            addCriterionForJDBCDate("JYRQ_U in", values, "JYRQ_U");
            return (Criteria) this;
        }

        public Criteria andJYRQ_UNotIn(List<Date> values) {
            addCriterionForJDBCDate("JYRQ_U not in", values, "JYRQ_U");
            return (Criteria) this;
        }

        public Criteria andJYRQ_UBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("JYRQ_U between", value1, value2, "JYRQ_U");
            return (Criteria) this;
        }

        public Criteria andJYRQ_UNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("JYRQ_U not between", value1, value2, "JYRQ_U");
            return (Criteria) this;
        }

        public Criteria andJYLS_UIsNull() {
            addCriterion("JYLS_U is null");
            return (Criteria) this;
        }

        public Criteria andJYLS_UIsNotNull() {
            addCriterion("JYLS_U is not null");
            return (Criteria) this;
        }

        public Criteria andJYLS_UEqualTo(String value) {
            addCriterion("JYLS_U =", value, "JYLS_U");
            return (Criteria) this;
        }

        public Criteria andJYLS_UNotEqualTo(String value) {
            addCriterion("JYLS_U <>", value, "JYLS_U");
            return (Criteria) this;
        }

        public Criteria andJYLS_UGreaterThan(String value) {
            addCriterion("JYLS_U >", value, "JYLS_U");
            return (Criteria) this;
        }

        public Criteria andJYLS_UGreaterThanOrEqualTo(String value) {
            addCriterion("JYLS_U >=", value, "JYLS_U");
            return (Criteria) this;
        }

        public Criteria andJYLS_ULessThan(String value) {
            addCriterion("JYLS_U <", value, "JYLS_U");
            return (Criteria) this;
        }

        public Criteria andJYLS_ULessThanOrEqualTo(String value) {
            addCriterion("JYLS_U <=", value, "JYLS_U");
            return (Criteria) this;
        }

        public Criteria andJYLS_ULike(String value) {
            addCriterion("JYLS_U like", value, "JYLS_U");
            return (Criteria) this;
        }

        public Criteria andJYLS_UNotLike(String value) {
            addCriterion("JYLS_U not like", value, "JYLS_U");
            return (Criteria) this;
        }

        public Criteria andJYLS_UIn(List<String> values) {
            addCriterion("JYLS_U in", values, "JYLS_U");
            return (Criteria) this;
        }

        public Criteria andJYLS_UNotIn(List<String> values) {
            addCriterion("JYLS_U not in", values, "JYLS_U");
            return (Criteria) this;
        }

        public Criteria andJYLS_UBetween(String value1, String value2) {
            addCriterion("JYLS_U between", value1, value2, "JYLS_U");
            return (Criteria) this;
        }

        public Criteria andJYLS_UNotBetween(String value1, String value2) {
            addCriterion("JYLS_U not between", value1, value2, "JYLS_U");
            return (Criteria) this;
        }

        public Criteria andJYM_UUIsNull() {
            addCriterion("JYM_UU is null");
            return (Criteria) this;
        }

        public Criteria andJYM_UUIsNotNull() {
            addCriterion("JYM_UU is not null");
            return (Criteria) this;
        }

        public Criteria andJYM_UUEqualTo(String value) {
            addCriterion("JYM_UU =", value, "JYM_UU");
            return (Criteria) this;
        }

        public Criteria andJYM_UUNotEqualTo(String value) {
            addCriterion("JYM_UU <>", value, "JYM_UU");
            return (Criteria) this;
        }

        public Criteria andJYM_UUGreaterThan(String value) {
            addCriterion("JYM_UU >", value, "JYM_UU");
            return (Criteria) this;
        }

        public Criteria andJYM_UUGreaterThanOrEqualTo(String value) {
            addCriterion("JYM_UU >=", value, "JYM_UU");
            return (Criteria) this;
        }

        public Criteria andJYM_UULessThan(String value) {
            addCriterion("JYM_UU <", value, "JYM_UU");
            return (Criteria) this;
        }

        public Criteria andJYM_UULessThanOrEqualTo(String value) {
            addCriterion("JYM_UU <=", value, "JYM_UU");
            return (Criteria) this;
        }

        public Criteria andJYM_UULike(String value) {
            addCriterion("JYM_UU like", value, "JYM_UU");
            return (Criteria) this;
        }

        public Criteria andJYM_UUNotLike(String value) {
            addCriterion("JYM_UU not like", value, "JYM_UU");
            return (Criteria) this;
        }

        public Criteria andJYM_UUIn(List<String> values) {
            addCriterion("JYM_UU in", values, "JYM_UU");
            return (Criteria) this;
        }

        public Criteria andJYM_UUNotIn(List<String> values) {
            addCriterion("JYM_UU not in", values, "JYM_UU");
            return (Criteria) this;
        }

        public Criteria andJYM_UUBetween(String value1, String value2) {
            addCriterion("JYM_UU between", value1, value2, "JYM_UU");
            return (Criteria) this;
        }

        public Criteria andJYM_UUNotBetween(String value1, String value2) {
            addCriterion("JYM_UU not between", value1, value2, "JYM_UU");
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