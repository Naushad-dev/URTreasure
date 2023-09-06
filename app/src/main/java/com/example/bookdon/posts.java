package com.example.bookdon;

public class posts {
 String image,postedBy,PostTitle,Description;
 long postedAt;

 public posts() {
 }



 public posts(String image, String postedBy, String PostTitle, String description, long postedAt) {
  this.image = image;
  this.postedBy = postedBy;
  this.PostTitle=PostTitle;

  this.Description = description;
  this.postedAt = postedAt;
 }

 public String getImage() {
  return image;
 }

 public void setImage(String image) {
  this.image = image;
 }
 public String getPostTitle() {
  return PostTitle;
 }

 public void setPostTitle(String postTitle) {
  PostTitle = postTitle;
 }



 public String getPostedBy() {
  return postedBy;
 }

 public void setPostedBy(String postedBy) {
  this.postedBy = postedBy;
 }

 public String getDescription() {
  return Description;
 }

 public void setDescription(String description) {
  Description = description;
 }

 public long getPostedAt(long time) {
  return postedAt;
 }

 public void setPostedAt(long postedAt) {
  this.postedAt = postedAt;
 }
}
