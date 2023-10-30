var teacher = document.getElementById("teacher");
var syllabus = document.getElementById("syllabus");
var book = document.getElementById("book");
var resource = document.getElementById("resource");
var notice = document.getElementById("notice");
var classSchedule = document.getElementById("classSchedule");
var featured = document.getElementById("featured");

function hideShowTeacher() {
  syllabus.style.display = 'none';
  book.style.display = 'none';
  resource.style.display = 'none';
  notice.style.display = 'none';
  classSchedule.style.display = 'none';
  featured.style.display = 'none';
  teacher.style.display = 'block';
  // if(teacher.style.display === 'none') {
  //   teacher.style.display = 'block';
  // }
  // else {
  //   teacher.style.display = 'none';
  // }
}

function hideShowSyllabus() {
  teacher.style.display = 'none';
  book.style.display = 'none';
  resource.style.display = 'none';
  notice.style.display = 'none';
  classSchedule.style.display = 'none';
  featured.style.display = 'none';
  syllabus.style.display = 'block';
  // if(syllabus.style.display === 'none') {
  //   syllabus.style.display = 'block';
  // }
  // else {
  //   syllabus.style.display = 'none';
  // }
}

function hideShowBook() {
  syllabus.style.display = 'none';
  teacher.style.display = 'none';
  resource.style.display = 'none';
  notice.style.display = 'none';
  classSchedule.style.display = 'none';
  featured.style.display = 'none';
  book.style.display = 'block';
  // if(book.style.display === 'none') {
  //   book.style.display = 'block';
  // }
  // else {
  //   book.style.display = 'none';
  // }
}

function hideShowResource() {
  syllabus.style.display = 'none';
  book.style.display = 'none';
  teacher.style.display = 'none';
  notice.style.display = 'none';
  classSchedule.style.display = 'none';
  featured.style.display = 'none';
  resource.style.display = 'block';
  // if(resource.style.display === 'none') {
  //   resource.style.display = 'block';
  // }
  // else {
  //   resource.style.display = 'none';
  // }
}

function hideShowNotice() {
  syllabus.style.display = 'none';
  book.style.display = 'none';
  teacher.style.display = 'none';
  resource.style.display = 'none';
  classSchedule.style.display = 'none';
  featured.style.display = 'none';
  notice.style.display = 'block';
  // if(notice.style.display === 'none') {
  //   notice.style.display = 'block';
  // }
  // else {
  //   notice.style.display = 'none';
  // }
}

function hideShowClassSchedule() {
  syllabus.style.display = 'none';
  book.style.display = 'none';
  teacher.style.display = 'none';
  resource.style.display = 'none';
  notice.style.display = 'none';
  featured.style.display = 'none';
  classSchedule.style.display = 'block';
  // if(classSchedule.style.display === 'none') {
  //   classSchedule.style.display = 'block';
  // }
  // else {
  //   classSchedule.style.display = 'none';
  // }
}

function hideShowFeatured() {
  syllabus.style.display = 'none';
  book.style.display = 'none';
  teacher.style.display = 'none';
  resource.style.display = 'none';
  notice.style.display = 'none';
  classSchedule.style.display = 'none';
  featured.style.display = 'block';

  // if(featured.style.display === 'none') {
  //   featured.style.display = 'block';
  // }
  // else {
  //   featured.style.display = 'none';
  // }
}