package com.anye.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.table.Admin;
import com.table.Buyer;
import com.table.Product;
import com.table.Saler;

import com.anye.greendao.gen.AdminDao;
import com.anye.greendao.gen.BuyerDao;
import com.anye.greendao.gen.ProductDao;
import com.anye.greendao.gen.SalerDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig adminDaoConfig;
    private final DaoConfig buyerDaoConfig;
    private final DaoConfig productDaoConfig;
    private final DaoConfig salerDaoConfig;

    private final AdminDao adminDao;
    private final BuyerDao buyerDao;
    private final ProductDao productDao;
    private final SalerDao salerDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        adminDaoConfig = daoConfigMap.get(AdminDao.class).clone();
        adminDaoConfig.initIdentityScope(type);

        buyerDaoConfig = daoConfigMap.get(BuyerDao.class).clone();
        buyerDaoConfig.initIdentityScope(type);

        productDaoConfig = daoConfigMap.get(ProductDao.class).clone();
        productDaoConfig.initIdentityScope(type);

        salerDaoConfig = daoConfigMap.get(SalerDao.class).clone();
        salerDaoConfig.initIdentityScope(type);

        adminDao = new AdminDao(adminDaoConfig, this);
        buyerDao = new BuyerDao(buyerDaoConfig, this);
        productDao = new ProductDao(productDaoConfig, this);
        salerDao = new SalerDao(salerDaoConfig, this);

        registerDao(Admin.class, adminDao);
        registerDao(Buyer.class, buyerDao);
        registerDao(Product.class, productDao);
        registerDao(Saler.class, salerDao);
    }
    
    public void clear() {
        adminDaoConfig.clearIdentityScope();
        buyerDaoConfig.clearIdentityScope();
        productDaoConfig.clearIdentityScope();
        salerDaoConfig.clearIdentityScope();
    }

    public AdminDao getAdminDao() {
        return adminDao;
    }

    public BuyerDao getBuyerDao() {
        return buyerDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public SalerDao getSalerDao() {
        return salerDao;
    }

}
