package com.entvy.ex25_branch.mapper;

import com.entvy.ex25_branch.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    // 모든 학생 조회
    List<Student> findAll();

    // 특정 학생 조회
    @Select("SELECT * FROM students WHERE id = #{id}")
    Student findById(Long id);

    // 학생 추가
    @Insert("INSERT INTO students(NAME, email, age) \r\n"
			+ "  	VALUES (#{name}, #{email}, #{age})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Student student);

    // 학생 정보 수정
    @Update("UPDATE students\r\n"
			+ "	SET NAME = #{name}, email = #{email}, age = #{age}\r\n"
			+ "	WHERE id = #{id}")
    void update(Student student);

    // 학생 제거
    @Delete("DELETE FROM students WHERE id = #{id}")
    void delete(Long id);

    // Email 중복 확인
    boolean existsByEmail(String email);
}
