package com.example.sale.serviceImpl;

import com.example.sale.dao.*;
import com.example.sale.model.*;
import com.example.sale.service.OrderService;
import com.example.sale.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: OrderServiceImpl
 * @Description: TODO
 * @Author: PANLVZ
 * @Date: 2020-03-03
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private TsalesMapper tsalesMapper;

    @Resource
    private TpartyMapper tpartyMapper;

    @Resource
    private TconsigneeMapper tconsigneeMapper;

    @Resource
    private TproductMapper tproductMapper;

    @Resource
    private TsalesProductMapper tsalesProductMapper;

    /**
     * 新增订单
     * @param tsales
     */
    @Override
    public void addOrder(Tsales tsales) {
        tsales.setId(UuidUtil.getUuid());
        tsales.setCreateTime(new Date());
        addParty(tsales.getParty());
        addConsignee(tsales.getConsignee());
        addSalesProducts(tsales);
        addProduct(tsales);
        tsalesMapper.insert(tsales);
    }

    @Override
    public List<Tsales> selectAll(){
        return tsalesMapper.selectAll();
    }

    /**
     * 根据关键词查询订单  分页
     * @param keyword
     * @param index
     * @param size
     * @return
     */
    @Override
    public List<Tsales> selectByKeyword(String keyword, int index, int size) {
        List<Tsales> list = tsalesMapper.selectByKeyword(keyword, index, size);
        for(int i = 0 ; i < list.size(); i++) {
            List<TsalesProduct> products = new ArrayList<>();
            products = tsalesProductMapper.selectById(list.get(i).getId());
            list.get(i).setProducts(products);
        }
        return list;
    }

    /**
     * 根据关键词查询订单总数 用于分页
     * @param keyword
     * @return
     */
    @Override
    public Integer selectCountByKeyword(String keyword) {
        return tsalesMapper.selectCountByKeyword(keyword);
    }

    /**
     * 获取总销售额
     * @param startTime
     * @param endTime
     * @param party
     * @param consignee
     * @return
     */
    @Override
    public double getTotalPrice(String startTime, String endTime,
                                String party, String consignee) {
        return tsalesMapper.getTotalPrice(startTime, endTime, party, consignee);
    }

    /**
     * 获取甲方
     * @param key
     * @return
     */
    @Override
    public PageResult<Tparty> selectParty(String key) {
        PageResult result = new PageResult();
        List<Tparty> list = tpartyMapper.selectByKey(key);
        if (list != null) {
            result.setList(list);
            result.setCount(tpartyMapper.selectCountByKey(key));
        }
        return result;
    }

    /**
     * 获取收货方
     * @param key
     * @return
     */
    @Override
    public PageResult<Tconsignee> selectConsignee(String key) {
        PageResult result = new PageResult();
        List<Tconsignee> list = tconsigneeMapper.selectByKey(key);
        if (list != null) {
            result.setList(list);
            result.setCount(tconsigneeMapper.selectCountByKey(key));
        }
        return result;
    }

    /**
     * 获取产品甲方-产品信息
     * @param party
     * @param product
     * @return
     */
    @Override
    public PageResult<Tproduct> selectProduct(String party, String product) {
        PageResult result = new PageResult();
        List<Tproduct> list = tproductMapper.selectByKey(party, product);
        if (list != null) {
            result.setList(list);
            result.setCount(tproductMapper.selectCountByKey(party, product));
        }
        return result;
    }

    /**
     * 查询订单信息
     * @param startTime
     * @param endTime
     * @param party
     * @param consignee
     * @param product
     * @return
     */
    @Override
    public List<Tsales> selectSales(String startTime, String endTime,
                                    String party, String consignee, String product) {
        List<Tsales> list = tsalesMapper.selectByKey(startTime, endTime, party, consignee, product);
        for(int i = 0 ; i < list.size(); i++) {
            List<TsalesProduct> products = new ArrayList<>();
            products = tsalesProductMapper.selectByProductAndId(list.get(i).getId(), product);
            list.get(i).setProducts(products);
        }
        return list;
    }

    //新增甲方
    private void addParty(String newParty) {
        List<Tparty> list = tpartyMapper.selectAll();
        if (list.size() > 0 && list != null) {
            for(int i = 0 ; i < list.size(); i++) {
                if (list.get(i).getParty().equals(newParty)) {
                    return;
                }
            }
        }

        Tparty tparty = new Tparty();
        tparty.setParty(newParty);
        tpartyMapper.insert(tparty);
    }

    //新增收货方
    private void addConsignee(String newConsignee) {
        List<Tconsignee> list = tconsigneeMapper.selectAll();
        if (list.size() > 0 && list != null) {
            for(int i = 0 ; i < list.size(); i++) {
                if (list.get(i).getConsignee().equals(newConsignee)) {
                    return;
                }
            }
        }

        Tconsignee tconsignee = new Tconsignee();
        tconsignee.setConsignee(newConsignee);
        tconsigneeMapper.insert(tconsignee);
    }

    //新增产品信息
    private void addProduct(Tsales tsales) {
        List<Tproduct> list = tproductMapper.selectAll();
        Tsales products = new Tsales();
        products = tsales;
        if (list.size() > 0 && list != null) {
            boolean partySign = true;
            //新增甲方 全插
            for(int i = 0 ; i < list.size(); i++) {
                if (list.get(i).getParty().equals(products.getParty())) {
                    partySign = false;
                    break;
                }
            }
            if (partySign) {
                Tproduct tproduct = new Tproduct();
                tproduct.setParty(products.getParty());
                for(int i = 0 ; i < products.getProducts().size(); i++) {
                    tproduct.setProduct(products.getProducts().get(i).getProduct());
                    tproductMapper.insert(tproduct);
                }
            } else {
                //新增产品
                for(int i = 0 ; i < list.size(); i++) {
                    if (list.get(i).getParty().equals(products.getParty())) {
                        for(int j = 0 ; j < products.getProducts().size(); j++) {
                            String newProduct = products.getProducts().get(j).getProduct();
                            if (list.get(i).getProduct().equals(newProduct)) {
                                products.getProducts().remove(j);
                                break;
                            }
                        }
                    }
                }
                if (products.getProducts().size() > 0) {
                    Tproduct tproduct = new Tproduct();
                    tproduct.setParty(products.getParty());
                    for(int i = 0 ; i < products.getProducts().size(); i++) {
                        tproduct.setProduct(products.getProducts().get(i).getProduct());
                        tproductMapper.insert(tproduct);
                    }
                }
            }
        } else {
            //库内为空  全插
            Tproduct tproduct = new Tproduct();
            tproduct.setParty(products.getParty());
            for(int i = 0 ; i < products.getProducts().size(); i++) {
                tproduct.setProduct(products.getProducts().get(i).getProduct());
                tproductMapper.insert(tproduct);
            }
        }
    }

    //新增产品订单信息
    private void addSalesProducts(Tsales tsales) {
        for(int i = 0 ; i < tsales.getProducts().size(); i++) {
            TsalesProduct tsalesProduct = new TsalesProduct();
            tsalesProduct.setId(tsales.getId());
            tsalesProduct.setProduct(tsales.getProducts().get(i).getProduct());
            tsalesProduct.setNumber(tsales.getProducts().get(i).getNumber());
            tsalesProduct.setSize(tsales.getProducts().get(i).getSize());
            tsalesProduct.setPrice(tsales.getProducts().get(i).getPrice());
            tsalesProductMapper.insert(tsalesProduct);
        }
    }
}
