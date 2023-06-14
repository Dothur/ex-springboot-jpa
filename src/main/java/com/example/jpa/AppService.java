package com.example.jpa;

import com.example.jpa.entities.StudentEntity;
import com.example.jpa.repos.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppService {
    private final AppRepository repository;
    // JPARepository
    private final StudentRepository studentRepository;

    // repo 의존성 주입
    public AppService(AppRepository repository, StudentRepository studentRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
    }

    // 주된 비즈니스 로직이 구현되는 공간
    // Controller -> Service
    // Service
    // 1. 데이터베이스 조회
    // 2. Component 사용
    // 3. 모은 데이터를 가지고 응답 (의사결정)
//    public List<Object> readStudentAll() {
//        List<Object> queryResult = repository.selectStudentAll();
//        return queryResult;
//    }

    // CREATE
    public void createStudent(
            String name,
            Integer age,
            String phone,
            String email
    ) {
        // 새로운 (new)  Student (entity)를 만들고싶다
        StudentEntity newEntity = new StudentEntity();
        newEntity.setName(name);
        newEntity.setAge(age);
        newEntity.setPhone(phone);
        newEntity.setEmail(email);
        // repository.save 저장
        this.studentRepository.save(newEntity);
    }

    // READ
    public void readStudent(Long id){
        Optional<StudentEntity> optionalStudentEntity
                = this.studentRepository.findById(id);
        // 1. 실제 데이터가 온 경우
        if (optionalStudentEntity.isPresent()){
            // 출력한다
            System.out.println(optionalStudentEntity.get());
        }
        // 2. 결과가 null 이 되었을 경우
        else {
            System.out.println("no result");
        }
//        System.out.println(this.studentRepository.findById(id));
    }

    // READ ALL
    public void readStudentAll(){
        System.out.println(this.studentRepository.findAll());
    }

    // UPDATE
    public void updateStudent(
            // 어떤 대상을 수정할지 지정해줘야 함
            Long id,
            String name // 수정할 데이터
    ){
        // 수정할 Entity 를 회수
        Optional<StudentEntity> optionalEntity =
                this.studentRepository.findById(id);
        // 수정할 Entity 를 찾은경우
        if (optionalEntity.isPresent()){
            // 실제 객체 회수
            StudentEntity target = optionalEntity.get();
            // 수정할 데이터 적용
            target.setName(name);
            // Save
            this.studentRepository.save(target);
        } else {
            // 수정할 Entity 를 못찾거나 없으면
            System.out.println("Could not find");
        }
    }

    // DELETE update 랑 이제 거의 유사함
    public void deleteStudent(Long id){
        Optional<StudentEntity> optionalEntity
                = this.studentRepository.findById(id);
        if (optionalEntity.isPresent()){
            StudentEntity studentEntity = optionalEntity.get();
            this.studentRepository.delete(studentEntity);
        } else {
            System.out.println("Could not find");
        }
    }

    // findAllBy
    public void findAllByTest(){
        // findAllByOrderByName
        System.out.println("findAllByOrderByName Test.");
        List<StudentEntity> studentEntities =
                this.studentRepository.findAllByOrderByName();
        for (int i = 0; i < 5; i++) {
            System.out.println(studentEntities.get(i));
        }
        System.out.println("..........");

        // findAllByOrderByAgeDesc
        System.out.println("findAllByOrderByAgeDesc Test.");
        studentEntities =
                this.studentRepository.findAllByOrderByAgeDesc();
        for (int i = 0; i < 5; i++) {
            System.out.println(studentEntities.get(i));
        }
        System.out.println("..........");

        // findAllByAgeLessThan
        System.out.println("findAllByAgeLessThan Test.");
        studentEntities =
                this.studentRepository.findAllByAgeLessThan(21);
        for (int i = 0; i < 5; i++) {
            System.out.println(studentEntities.get(i));
        }
        System.out.println("..........");

        // findAllByPhoneStartingWith
        System.out.println("findAllByPhoneStartingWith Test.");
        studentEntities =
                this.studentRepository.findAllByPhoneStartingWith("010-");
        for (int i = 0; i < 5; i++) {
            System.out.println(studentEntities.get(i));
        }
        System.out.println("..........");
    }
}
