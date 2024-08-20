# User-Management-System
User Management System

Проект включает реализацию модели пользователя, репозиториев для работы с базой данных, а также сервисов с поддержкой шаблонов JdbcTemplate и NamedParameterJdbcTemplate. Основные задачи проекта включают работу с базой данных, организацию конфигурации через контекст и написание интеграционных тестов.

## Задачи

### Реализовать модель User с полями: ###

1. Идентификатор (id);
2. Email (email).

### Реализовать интерфейс CrudRepository<T> с методами: ###

1. Optional<T> findById(Long id)
2. List<T> findAll()
3. void save(T entity)
4. void update(T entity)
5. void delete(Long id)

Интерфейс UsersRepository должен наследовать CrudRepository и включать метод:

- Optional<T> findByEmail(String email)
Реализовать две версии UsersRepository:

UsersRepositoryJdbcImpl (использует стандартные механизмы Statement).
UsersRepositoryJdbcTemplateImpl (основан на JdbcTemplate/NamedParameterJdbcTemplate).
Конфигурация контекста (context.xml):

Объявить бины с разными идентификаторами для обоих типов репозиториев.
Объявить два бина типа DataSource: DriverManagerDataSource и HikariDataSource.
Данные для подключения к базе данных должны быть указаны в файле db.properties и включены в context.xml с использованием плейсхолдеров ${db.url}, ${db.username}, ${db.password}.
Реализовать интеграционный тест для UsersServiceImp:

Используйте встроенную базу данных (H2 или HSQLDB).
Конфигурация контекста для тестовой среды (включая DataSource для встроенной базы данных) описывается в отдельном классе TestApplicationConfig.
Тест проверяет, возвращается ли временный пароль в методе signUp.