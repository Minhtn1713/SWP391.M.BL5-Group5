    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package model;

    /**
     *
     * @author kienk
     */
    public class ProductImage {
        private int id;
        private String url;
        private int productId;
        private int ramId;

        public ProductImage() {
        }

        public ProductImage(int id, String url, int productId) {
            this.id = id;
            this.url = url;
            this.productId = productId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getRamId() {
            return ramId;
        }

        public void setRamId(int ramId) {
            this.ramId = ramId;
        }

        public ProductImage(int id, String url, int productId, int ramId) {
            this.id = id;
            this.url = url;
            this.productId = productId;
            this.ramId = ramId;
        }


    }
