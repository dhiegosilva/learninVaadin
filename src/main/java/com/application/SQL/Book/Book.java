package com.application.SQL.Book;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BookTable")
public class Book {
   @Id
   @GeneratedValue
   private Long id;
   private String title;
   private String autor;
   private Date year;
   private Long isbn;
   private Double price;

   protected Book() {
   }

   public Book(String title, String autor, Date year, Long isbn, Double price) {
      this.title = title;
      this.autor = autor;
      this.year = year;
      this.isbn = isbn;
      this.price = price;      
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getAutor() {
      return autor;
   }

   public void setAutor(String autor) {
      this.autor = autor;
   }

   public Date getYear() {
      return year;
   }

   public void setYear(Date year) {
      this.year = year;
   }
   
   public Long getIsbn() {
      return isbn;
   }

   public void setIsbn(Long isbn) {
      this.isbn = isbn;
   }
   
   public Double getPrice() {
      return price;
   }

   public void setPrice(Double price) {
      this.price = price;
   }
}