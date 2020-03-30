package com.hinkmond.finalproj;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RestController
public class JDBCController {
    private final static String KEYFILEPATH = "./keyFile.key";


    @CrossOrigin
    @RequestMapping(value = "/printAllBooks", method = RequestMethod.GET)
    public String printAllBooks() {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        StringBuilder resultStr = new StringBuilder();
        resultStr.append("book_id,title, author, category, cost, inventory\n");

        String queryStr = "SELECT * from book_detail;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("book_id")).append(", ")
                    .append(sqlRowSet.getString("title")).append(", ")
                    .append(sqlRowSet.getString("author")).append(", ")
                    .append(sqlRowSet.getString("category")).append(", ")
                    .append(sqlRowSet.getString("cost")).append(", ")
                    .append(sqlRowSet.getString("inventory")).append(", ")
                    .append("\n");
        }
        return ("SELECT * from book_detail:\n" + resultStr);
    }

    @CrossOrigin
    @RequestMapping(value = "/printBookHasZeroInventory", method = RequestMethod.GET)
    public String printBookHasZeroInventory() {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        StringBuilder resultStr = new StringBuilder();
        resultStr.append("book_id,title, author, category, cost, inventory\n");

        String queryStr = "SELECT * from book_detail where inventory = 0;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("book_id")).append(", ")
                    .append(sqlRowSet.getString("title")).append(", ")
                    .append(sqlRowSet.getString("author")).append(", ")
                    .append(sqlRowSet.getString("category")).append(", ")
                    .append(sqlRowSet.getString("cost")).append(", ")
                    .append(sqlRowSet.getString("inventory")).append(", ")
                    .append("\n");
        }
        return ("SELECT * from book_detail where inventory = 0:\n" + resultStr);
    }

    @RequestMapping(value = "/showBookOnAllShelfs", method = RequestMethod.GET)
    public String showBookOnAllShelfs() {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        StringBuilder resultStr = new StringBuilder();
        resultStr.append("shelf_number, book_id, title , author, category, inventory , cost\n");

        String queryStr = "select t1.shelf_number, t2.book_id, t2.title, t2.author, t2.category, t2.inventory, t2.cost from book_store t1 INNER JOIN book_detail t2 ON t1.book_id = t2.book_id order by t1.shelf_number;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("shelf_number")).append(", ")
                    .append(sqlRowSet.getString("book_id")).append(", ")
                    .append(sqlRowSet.getString("title")).append(", ")
                    .append(sqlRowSet.getString("author")).append(", ")
                    .append(sqlRowSet.getString("category")).append(", ")
                    .append(sqlRowSet.getString("cost")).append(", ")
                    .append(sqlRowSet.getString("inventory")).append(", ")
                    .append("\n");
        }
        return ("select t1.shelf_number, t2.book_id, t2.title, t2.author, t2.category, t2.inventory, t2.cost from book_store t1 INNER JOIN book_detail t2 ON t1.book_id = t2.book_id order by t1.shelf_number;\n\n\n" + resultStr);
    }

    @CrossOrigin
    @RequestMapping(value = "/findBookOnShelf", method = RequestMethod.POST)
    public String findBookOnShelf(Integer shelfNum) {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        String queryStr = String.format("select t1.shelf_number, t2.book_id, t2.title, t2.author, t2.category, t2.inventory, t2.cost from book_store t1 INNER JOIN book_detail t2 ON t1.book_id = t2.book_id where t1.shelf_number = %s;", shelfNum);
        StringBuilder resultStr = new StringBuilder();
        resultStr.append("shelf_number, book_id, title , author, category, inventory , cost\n");
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("shelf_number")).append(", ")
                    .append(sqlRowSet.getString("book_id")).append(", ")
                    .append(sqlRowSet.getString("title")).append(", ")
                    .append(sqlRowSet.getString("author")).append(", ")
                    .append(sqlRowSet.getString("category")).append(", ")
                    .append(sqlRowSet.getString("cost")).append(", ")
                    .append(sqlRowSet.getString("inventory")).append(", ")
                    .append("\n");
        }
        return (String.format("%s\n\n%s\n", queryStr, resultStr));
    }

    @CrossOrigin
    @RequestMapping(value = "/findBookTitle", method = RequestMethod.POST)
    public String findBookTitle(@RequestBody Map<String, Object> title) {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        String pred = (String) title.get("title");
        String queryStr = String.format("select * from book_detail where title like '%%%s%%';", pred);
        StringBuilder resultStr = new StringBuilder();
        resultStr.append("book_id, title , author, category, cost, inventory\n");
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("book_id")).append(", ")
                    .append(sqlRowSet.getString("title")).append(", ")
                    .append(sqlRowSet.getString("author")).append(", ")
                    .append(sqlRowSet.getString("category")).append(", ")
                    .append(sqlRowSet.getString("cost")).append(", ")
                    .append(sqlRowSet.getString("inventory")).append(", ")
                    .append("\n");
        }
        return (String.format("%s\n\n%s\n", queryStr, resultStr));
    }

    @CrossOrigin
    @RequestMapping(value = "/getInventoryForBookId", method = RequestMethod.POST)
    public String getInventoryForBook(Integer bookid) {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        String queryStr = String.format("select book_id, title, author, inventory, category from book_detail where book_id = %s;" , bookid);
        StringBuilder resultStr = new StringBuilder();
        resultStr.append("book_id, title, author, inventory, category\n");
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("book_id")).append(", ")
                    .append(sqlRowSet.getString("title")).append(", ")
                    .append(sqlRowSet.getString("author")).append(", ")
                    .append(sqlRowSet.getString("inventory")).append(", ")
                    .append(sqlRowSet.getString("category")).append(", ")

                    .append("\n");
        }
        return (String.format("%s\n\n%s\n", queryStr, resultStr));
    }

    @CrossOrigin
    @RequestMapping(value = "/findPublisherName", method = RequestMethod.POST)
    public String findPublisherName(@RequestBody Map<String, Object> title) {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        String pred = (String) title.get("name");
        String queryStr = String.format("select * from publisher_info where publisher_name like '%%%s%%';", pred);
        StringBuilder resultStr = new StringBuilder();
        resultStr.append("publisher_id, book_id , publisher_name, city, state, country\n");
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("publisher_id")).append(", ")
                    .append(sqlRowSet.getString("book_id")).append(", ")
                    .append(sqlRowSet.getString("publisher_name")).append(", ")
                    .append(sqlRowSet.getString("city")).append(", ")
                    .append(sqlRowSet.getString("state")).append(", ")
                    .append(sqlRowSet.getString("country")).append(", ")
                    .append("\n");
        }
        return (String.format("%s\n\n%s\n", queryStr, resultStr));
    }

    @CrossOrigin
    @RequestMapping(value = "/findBookByCategory", method = RequestMethod.POST)
    public String findBookByCategory(@RequestBody Map<String, Object> title) {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        String pred = (String) title.get("name");
        String queryStr = String.format("select * from book_detail where  category  like '%%%s%%';", pred);
        StringBuilder resultStr = new StringBuilder();
        resultStr.append("book_id, title , author, category, cost, inventory\n");
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("book_id")).append(", ")
                    .append(sqlRowSet.getString("title")).append(", ")
                    .append(sqlRowSet.getString("author")).append(", ")
                    .append(sqlRowSet.getString("category")).append(", ")
                    .append(sqlRowSet.getString("cost")).append(", ")
                    .append(sqlRowSet.getString("inventory")).append(", ")
                    .append("\n");
        }
        return (String.format("%s\n\n%s\n", queryStr, resultStr));
    }

    @CrossOrigin
    @RequestMapping(value = "/addBookDetail", method = RequestMethod.POST)
    public String addBookDetail(@RequestBody AddBookDetail addBookDetail) {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        String query = String.format("INSERT INTO book_store (shelf_number)  VALUES (%s);", addBookDetail.getShelfNum());
        int rowsUpdated = jdbcTemplate.update(query);
        System.out.println(String.format("** Added new roll into book_store.  Row updated: %s", rowsUpdated));
        query = "select max(book_id) as newbookid from book_store;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(query);
        int bkid = -1;
        if (sqlRowSet != null) {
            if (sqlRowSet.next()) {
                bkid = sqlRowSet.getInt("newbookid");
                if (bkid != -1) {
                    String q = String.format("INSERT INTO book_detail (book_id, title, author, category, cost,inventory ) VALUES ('%s','%s','%s','%s', %s, %s)",
                            bkid, addBookDetail.getTitle(), addBookDetail.getAuthor(), addBookDetail.getCategory(), addBookDetail.getCost(), addBookDetail.getInventory());
                    System.out.println(q);
                    rowsUpdated = jdbcTemplate.update(q);
                }
            }
        }
        else {
            return ("Can't insert row in book_detail");
        }
        return (String.format("Number of row added into 'book_detail' and 'book_store' = %s  with book_id  = %s \n", rowsUpdated, bkid));
    }

    @CrossOrigin
    @RequestMapping(value = "/addPublisher", method = RequestMethod.POST)
    public String addPublisher(@RequestBody AddPublisher addPublisher) {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        String query = String.format("select * from book_store where book_id = %s;", addPublisher.getBookid());
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(query);
        String queryStr = "";
        if (sqlRowSet.next()){
            queryStr = String.format("INSERT INTO publisher_info (book_id, publisher_name, city, state, country) VALUES (%s,'%s','%s','%s', '%s')",
                    addPublisher.getBookid(), addPublisher.getPublisher_name(), addPublisher.getCity(), addPublisher.getState(), addPublisher.getCountry());
            System.out.println(queryStr);
            int rowsUpdated = jdbcTemplate.update(queryStr);
            return (String.format("** Added 1 row in publisher_info "));

        } else {
            return (String.format("Can't book_id = %s in book_store database table\n", addPublisher.getBookid()));
        }
    }
}
