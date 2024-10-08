package controller;

import dao.ProductDAO;
import dao.ProductVariantDAO;
import dao.SaleDAO;
import dao.StorageDAO;
import dao.RamDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Product;
import model.ProductImage;
import model.ProductVariant;
import model.ProductVariantInfomation;
import model.Sale;
import model.Storage;
import model.Ram;
import model.Range;
import utilities.Helper;

public class Admin_ProductVariantListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("run get");
        ProductDAO pDao = new ProductDAO();
        ProductVariantDAO productVarDao = new ProductVariantDAO();
        SaleDAO sale = new SaleDAO();
        String offsetRaw = request.getParameter("o");
        String fetchRaw = request.getParameter("f");
        String tagRaw = request.getParameter("tag");
        String sr = request.getParameter("sr");
        String search = (sr == null) ? "" : sr;
        List<Integer> listId = productVarDao.getListIdProductByName(search);
        List<String> ramF = new ArrayList<>();
        List<String> storageF = new ArrayList<>();
        String tag = (tagRaw == null) ? "1" : tagRaw;
        String filterRaw = request.getParameter("filter");
        String filter = (filterRaw == null) ? "1" : filterRaw;
        Range range = new Range(0, 3000);
        int totalProduct;
        int endPage;
        int offset = 1;
        int fetch = 6;
        List<ProductVariant> productVar;
        if (offsetRaw != null && fetchRaw != null) {
            offset = Integer.parseInt(offsetRaw);
            fetch = Integer.parseInt(fetchRaw);
            totalProduct = productVarDao.getTotalProductVariant(offset, fetch, "id", ramF, storageF, range, new ArrayList<>(), filter);
            endPage = Helper.getEndPage(totalProduct, fetch);
            productVar = productVarDao.getListProductVariant(offset, fetch, "id desc", ramF, storageF, range, new ArrayList<>(), filter);
        } else {
            totalProduct = productVarDao.getTotalProductVariant(1, 6, "id", ramF, storageF, range, new ArrayList<>(), filter);
            productVar = productVarDao.getListProductVariant(1, 6, "id desc", ramF, storageF, range, new ArrayList<>(), filter);
            endPage = Helper.getEndPage(totalProduct, 6);
        }
        List<ProductVariantInfomation> productVarInfo = new ArrayList<>();
        Product pr;
        ProductImage pi;
        for (ProductVariant p : productVar) {
            pr = pDao.getProductByID(p.getProductId() + "");
            pi = productVarDao.getOneProductVariantImage(p.getProductId(), p.getRamId() + "");
            productVarInfo.add(new ProductVariantInfomation(p.getId(), pr.getName(), pr.getProcessor(),
                    pr.getScreen_details(), pr.getOperatingSystem(), pr.getBattery(), pr.getGraphic_card(),
                    (pi != null ? pi.getUrl(): ""), productVarDao.getRamNameById(p.getRamId()),
                    productVarDao.getStorageSizeById(p.getStorageId()), p.getQuantity(), p.getVariantPrice(), p.getStatus(), sale.getSaleById(p.getSaleId()).getPercent()));
        }
        List<Sale> saleList = sale.getListSale();
        request.setAttribute("filter", filter);
        request.setAttribute("saleList", saleList);
        request.setAttribute("sr", search);
        request.setAttribute("totalProduct", totalProduct);
        request.setAttribute("o", offset);
        request.setAttribute("f", fetch);
        request.setAttribute("tag", tag);
        request.setAttribute("totalPage", endPage);
        request.setAttribute("product", productVarInfo);
        request.getRequestDispatcher("screens/Admin_ProductionVariantList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("run post");
        ProductDAO proDao = new ProductDAO();
        ProductVariantDAO variantDao = new ProductVariantDAO();
        StorageDAO stoDao = new StorageDAO();
        RamDAO ramDao = new RamDAO();

         String id = (String) req.getParameter("pro");
        List<Product> list = proDao.getListProduct();
        List<ProductVariant> list_variant = variantDao.getListProductVariantByID("%%");
        if(id != "%%")
            list_variant = variantDao.getListProductVariantByID(id);
        HashMap<Integer, String> storageMap = (HashMap<Integer, String>) stoDao.getHashMapStorage();
        HashMap<Integer, String> ramMap = (HashMap<Integer, String>) ramDao.getHashMapRam();
        HashMap<Integer, String> productMap = (HashMap<Integer, String>)  proDao.getHashMapProduct();

        req.setAttribute("storageMap", storageMap);
        req.setAttribute("ramMap", ramMap);
        req.setAttribute("productMap", productMap);
        req.setAttribute("id", id);
        req.setAttribute("variant", list_variant);
        req.setAttribute("list", list);
        req.getRequestDispatcher("screens/Admin_ProductionVariantList.jsp").forward(req, resp);
    }
    
}
