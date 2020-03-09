package com.example.sale.serviceImpl;

import com.example.sale.dao.*;
import com.example.sale.model.*;
import com.example.sale.service.OrderService;
import com.example.sale.util.UUidUtil;
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

    @Override
    public void addOrder(Tsales tsales) {
        tsales.setId(UUidUtil.getUuid());
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

    @Override
    public Integer selectCountByKeyword(String keyword) {
        return tsalesMapper.selectCountByKeyword(keyword);
    }

    @Override
    public double getTotalPrice(String startTime, String endTime,
                                String party, String consignee) {
        return tsalesMapper.getTotalPrice(startTime, endTime, party, consignee);
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
