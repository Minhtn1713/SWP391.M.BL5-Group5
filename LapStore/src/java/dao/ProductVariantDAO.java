/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.RAM;
import model.ProductImage;
import model.ProductVariant;
import model.ProductVariantInfomation;
import model.Range;
import model.Storage;

/**
 *
 * @author kienk
 */
public class ProductVariantDAO extends DBContext {
    
    public List<ProductVariant> getListProductVariant(int offset, int fetch, 
            String orderBy, List<String> filterRam, List<String> filterStorage,
            Range range, List<Integer> search, String filter) {
        List<ProductVariant> product = new ArrayList<>();
        String query = "select * from productVariant"
                + " where 1=1";
        
        int min = range.getMin();
        int max = range.getMax();
        query += "AND (variant_price BETWEEN "+min +" AND "+max+") ";
        if (!filterRam.isEmpty()) {
            query += " and ";
            if (filterRam.size() == 1){
                query+= "Ram_id =" + filterRam.get(0);
            }
            if (filterRam.size()>1){
                query+= "(";
            
            for (int i = 0; i < filterRam.size(); i++) {
                if (i <=filterRam.size() - 2){
                    query+="Ram_id = " + filterRam.get(i) + " or ";
                }if (i == filterRam.size() -1){
                    query+="Ram_id = " + filterRam.get(i) + ") ";
                }
               
            }
            }
        }
        if (!filterStorage.isEmpty()) {
            query += " and ";
            if (filterStorage.size() == 1){
                query+= "storage_id =" + filterStorage.get(0);
            }
            if (filterStorage.size()>1){
                query+= "(";
            
            for (int i = 0; i < filterStorage.size(); i++) {              
                if (i <=filterStorage.size() - 2){
                    query+="storage_id = " + filterStorage.get(i) + " or ";
                }
                if (i == filterStorage.size() -1){
                    query+="storage_id = " + filterStorage.get(i) + ") ";
                }
               
            }
            }
        }
        if (!search.isEmpty()) {
            query += " and ";
            if (search.size() == 1){
                query+= "product_id =" + search.get(0);
            }
            if (search.size()>1){
                query+= "(";
            
            for (int i = 0; i < search.size(); i++) {              
                if (i <=search.size() - 2){
                    query+="product_id = " + search.get(i) + " or ";
                }
                if (i == search.size() -1){
                    query+="product_id = " + search.get(i) + ") ";
                }
               
            }
            }
        }else{
            query += " and product_id=0" ;
        }
        if (filter.equals("2")){
            query+= "and status = 1";
        } else if(filter.equals("3")){
            query+= "and status = 2";
        }else if(filter.equals("4")){
            query+= "and sale_id != 1";
        }
        query += " order by  " + orderBy;
        query += "  offset ? rows fetch next ? rows only";
        int count = 1;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(count++, (offset - 1) * fetch);
            ps.setInt(count++, fetch);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product.add(new ProductVariant(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7), rs.getInt(8)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return product;
    }
    

    public ProductImage getOneProductVariantImage(int id, String ramId) {
        String query = "Select top(1) * from ProductImage "
                + " Where productImage.product_id = ? ";
        if (!ramId.equals("")) {
            query += " AND Ram_id =  " + ramId;
        }
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return (new ProductImage(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getInt(4)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public int getTotalProductVariant() {
        String query = "select count(*) from productVariant";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    
    public int getTotalProductVariant(int offset, int fetch, String orderBy, 
            List<String> filterRam, List<String> filterStorage, Range range, 
            List<Integer> search, String filter) {
        String query = "select count(*) from productVariant"
                + " where 1=1";
        
        int min = range.getMin();
        int max = range.getMax();
        query += "AND (variant_price BETWEEN "+min +" AND "+max+") ";
        if (!filterRam.isEmpty()) {
            query += " and ";
            if (filterRam.size() == 1){
                query+= "Ram_id =" + filterRam.get(0);
            }
            if (filterRam.size()>1){
                query+= "(";
            
            for (int i = 0; i < filterRam.size(); i++) {
                if (i <=filterRam.size() - 2){
                    query+="Ram_id = " + filterRam.get(i) + " or ";
                }if (i == filterRam.size() -1){
                    query+="Ram_id = " + filterRam.get(i) + ") ";
                }
               
            }
            }
        }
        if (!filterStorage.isEmpty()) {
            query += " and ";
            if (filterStorage.size() == 1){
                query+= "storage_id =" + filterStorage.get(0);
            }
            if (filterStorage.size()>1){
                query+= "(";
            
            for (int i = 0; i < filterStorage.size(); i++) {              
                if (i <=filterStorage.size() - 2){
                    query+="storage_id = " + filterStorage.get(i) + " or ";
                }
                if (i == filterStorage.size() -1){
                    query+="storage_id = " + filterStorage.get(i) + ") ";
                }
               
            }
            }
        }
        if (!search.isEmpty()) {
            query += " and ";
            if (search.size() == 1){
                query+= "product_id =" + search.get(0);
            }
            if (search.size()>1){
                query+= "(";
            
            for (int i = 0; i < search.size(); i++) {              
                if (i <=search.size() - 2){
                    query+="product_id = " + search.get(i) + " or ";
                }
                if (i == search.size() -1){
                    query+="product_id = " + search.get(i) + ") ";
                }
               
            }
            }
        }else{
            query += " and product_id=0" ;
        }
        if (filter.equals("2")){
            query+= "and status = 1";
        } else if(filter.equals("3")){
            query+= "and status = 2";
        }else if(filter.equals("4")){
            query+= "and sale_id != 1";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public String getColorNameById(int id) {
        String query = "Select ram from ram "
                + "Where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public String getStorageSizeById(int id) {
        String query = "Select [Storage_Size] from [DURIAN_SHOP].[dbo].[Storage] "
                + "Where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<RAM> getListColor() {
        List<RAM> ram = new ArrayList<>();
        String query = "SELECT * FROM Ram";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ram.add(new RAM(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(5)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ram;
    }

    public List<Storage> getListStorage() {
        List<Storage> storage = new ArrayList<>();
        String query = "SELECT * FROM storage";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                storage.add(new Storage(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return storage;
    }
    
    public List<Integer> getListIdProductByName(String search){
        List<Integer> list = new ArrayList<>();
        String query = "Select id from product "
                + "where name like '%"+ search+ "%'";
         try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public ProductVariant getProductVariantByID(int id){
        ProductVariant product = new ProductVariant();
        String sql = "SELECT * FROM [ProductVariant] WHERE id LIKE '" + id + "'";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new ProductVariant(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7), rs.getInt(8));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
         return null;
    } 
    
    public ProductVariant getProductVariant(String ramId, String productId, String storageId){
        ProductVariant product = new ProductVariant();
        String sql = "SELECT * FROM [ProductVariant] WHERE product_id = " + productId + " and Ram_id = " +ramId+ " and storage_id = " + storageId;
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new ProductVariant(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7), rs.getInt(8));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
         return null;
    } 
    
    public ProductVariant getColdProductVariant(){
        ProductVariant cold_product = new ProductVariant();
        String sql = "select * from [ProductVariant] where Quantity = (select MAX(Quantity) from [ProductVariant]) ";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new ProductVariant(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7), rs.getInt(8));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
         return null;
    } 
    
     public List<ProductVariant> getListProductVariantByID(String id){
        List<ProductVariant> variant_list = new ArrayList<>();
        String sql = "SELECT * FROM [ProductVariant] WHERE Product_Id LIKE  '" + id + "'";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                variant_list.add(new ProductVariant(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7), rs.getInt(8)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
         return variant_list;
    } 
     
     public void deleteAllProductVariant(String product_id){
         String sql = "Delete FROM [ProductVariant] WHERE Product_Id LIKE  '" + product_id + "'";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
     
      public void updateAllProductVariant(String product_id, String status){
         String sql = "Update [ProductVariant] SET status ="+ status + " WHERE Product_Id LIKE  '" + product_id + "'";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
      
      public void updateAllProductVariantByColor(String Ram_id, String status){
         String sql = "Update [ProductVariant] SET status ="+ status + " WHERE Ram_id LIKE  '" + Ram_id+ "'";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
      
      public void updateAllProductVariantByStorage(String storage_id, String status){
         String sql = "Update [ProductVariant] SET status ="+ status + " WHERE storage_Id LIKE  '" + storage_id+ "'";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public int deleteProductVariant(String id){
        int succes = 0;
         String sql = "Delete FROM [ProductVariant] WHERE Id LIKE  '" + id + "'";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            succes = ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
         return succes;
    }
    
    public int updateProduct( ProductVariant pro_variant){
        int succes = 0;
        String query = "UPDATE [ProductVariant] SET Ram_id = " + pro_variant.getRam() + 
                                       ", Storage_Id = " + pro_variant.getStorage() +
                                       ", Quantity = " + pro_variant.getQuantity() +
                                       ", Variant_Price = " + pro_variant.getVariantPrice() +
                                       " where Id = " + pro_variant.getId();
          try{
            PreparedStatement ps = connection.prepareStatement(query);
            succes = ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
          return succes;
    }
    
      public int updateProduct(String id, String status){
        int succes = 0;
        String query = "UPDATE [ProductVariant] SET [Status] = " + status + 
                                       
                                       " where Id = " + id;
          try{
            PreparedStatement ps = connection.prepareStatement(query);
            succes = ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
          return succes;
    }
      public int updateProductSale(String id, String sale){
        int succes = 0;
        String query = "UPDATE [ProductVariant] SET [sale_id] = " + sale + 
                                       
                                       " where Id = " + id;
          try{
            PreparedStatement ps = connection.prepareStatement(query);
            succes = ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
          return succes;
    }
    
    public int createVariant(ProductVariant pro_variant){
        int succes = 0;
        System.out.println(pro_variant);
        String query = "INSERT INTO [ProductVariant]([Product_Id],[Ram_Id],[Storage_Id],[Quantity],[Variant_Price])"
                + "values ('" + pro_variant.getProduct_Id() + "', '" + pro_variant.getRam() + "' ,'" + pro_variant.getStorage()
                + "' ,'" + pro_variant.getQuantity() + "', '" + pro_variant.getVariantPrice() + "')";
          try{
            PreparedStatement ps = connection.prepareStatement(query);
            succes = ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
          return succes;
    }
    
     public ProductVariant productVariantIsExist(int productId, int ramId, int storageId){
        String sql = "SELECT * FROM [ProductVariant] WHERE product_Id = ? and Ram_id = ? and storage_id = ?";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setInt(2, ramId);
            ps.setInt(3, storageId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new ProductVariant(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7), rs.getInt(8));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
         return null;
    } 
public List<ProductVariantInfomation> getListNameProductVariantById(List<Integer> id) {
    List<ProductVariantInfomation> productVariants = new ArrayList<>();
    StringBuilder query = new StringBuilder(
        "SELECT pv.id AS productVariant_id, p.name AS product_name, r.ram AS ram_name, s.storage_Size AS storage_name, " +
        "pv.processor, pv.screenDetails, pv.size, pv.operatingSystem, pv.battery, pv.weight, pv.graphicCard, " +
        "pv.url, pv.quantity, pv.variantPrice, pv.sale_id, pv.status, pv.sale " +
        "FROM ProductVariant pv " +
        "JOIN product p ON pv.product_id = p.id " +
        "JOIN ram r ON pv.ram_id = r.id " +
        "JOIN storage s ON pv.storage_id = s.id WHERE "
    );

    if (!id.isEmpty()) {
        query.append("pv.id IN (");
        for (int i = 0; i < id.size(); i++) {
            query.append("?");
            if (i < id.size() - 1) {
                query.append(", ");
            }
        }
        query.append(")");
    }

    try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
        // Set the id parameters dynamically
        for (int i = 0; i < id.size(); i++) {
            ps.setInt(i + 1, id.get(i));
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            productVariants.add(new ProductVariantInfomation(
                rs.getInt("productVariant_id"),
                rs.getString("product_name"),
                rs.getString("processor"),
                rs.getString("screenDetails"),
                rs.getString("size"),
                rs.getString("operatingSystem"),
                rs.getString("battery"),
                rs.getFloat("weight"),
                rs.getString("graphicCard"),
                rs.getString("url"),
                rs.getInt("ram_name"),
                rs.getInt("storage_name"),
                rs.getInt("quantity"),
                rs.getFloat("variantPrice"),
                rs.getInt("sale_id"),
                rs.getInt("status"),
                rs.getFloat("sale")
            ));
        }

    } catch (SQLException e) {
        System.out.println(e);
    }

    return productVariants;
}

     public int getRemainById(int variantId) {
        int remain = 0;
        String sql = "select [Quantity] from ProductVariant where [Id] = " + variantId;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                remain = rs.getInt(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return remain;
    }

    public double getPriceById(int variantId) {
        double price = 0;
        String sql = "select [Variant_Price] from ProductVariant where [Id] " + variantId;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                price = rs.getDouble(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return price;
    }

    public int minusQuantity(int id, int quantity) {
        int succes = 0;
        String sql = "update [ProductVariant] set [Quantity] = [Quantity] - ? where Id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, id);
            succes = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return succes;
    }

}
