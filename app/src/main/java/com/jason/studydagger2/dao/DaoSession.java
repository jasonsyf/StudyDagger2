package com.jason.studydagger2.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.jason.studydagger2.dao.WxNewsDaoBean;

import com.jason.studydagger2.dao.WxNewsDaoBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig wxNewsDaoBeanDaoConfig;

    private final WxNewsDaoBeanDao wxNewsDaoBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        wxNewsDaoBeanDaoConfig = daoConfigMap.get(WxNewsDaoBeanDao.class).clone();
        wxNewsDaoBeanDaoConfig.initIdentityScope(type);

        wxNewsDaoBeanDao = new WxNewsDaoBeanDao(wxNewsDaoBeanDaoConfig, this);

        registerDao(WxNewsDaoBean.class, wxNewsDaoBeanDao);
    }
    
    public void clear() {
        wxNewsDaoBeanDaoConfig.clearIdentityScope();
    }

    public WxNewsDaoBeanDao getWxNewsDaoBeanDao() {
        return wxNewsDaoBeanDao;
    }

}