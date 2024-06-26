package br.com.semeru.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.semeru.data.vo.v1.BookVO;
import br.com.semeru.model.Book;

public class MockBook {

	public Book mockEntity() {
		return mockEntity(0);
	}
	
	public BookVO mockVO() {
		return mockVO(0);
	}

	public List<Book> mockEntityList() {
		List<Book> books = new ArrayList<>();
		
		for (int i = 0; i < 14; i++) {
			books.add(mockEntity(i));
		}
		
		return books;
	}
	
	public List<BookVO> mockVOList() {
		List<BookVO> books = new ArrayList<>();
		
		for (int i = 0; i < 14; i++) {
			books.add(mockVO(i));
		}
		
		return books;
	}
	
	public Book mockEntity(Integer number) {
		Book book = new Book();
		
		book.setId(number.longValue());
		book.setAuthor("Author Name" + number);
		book.setLaunchDate(new Date());
		book.setPrice(5.0 * (1+ number));
		book.setTitle("Title Name" + number);
		
		return book;
	}
	
	public BookVO mockVO(Integer number) {
		BookVO book = new BookVO();
		
		book.setKey(number.longValue());
		book.setAuthor("Author Name" + number);
		book.setLaunchDate(new Date());
		book.setPrice(5.0 * (1+ number));
		book.setTitle("Title Name" + number);
		

		return book;	}
}
