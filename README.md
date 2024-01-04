# ORM e JPA

## ORM - Object Relational Mapper
ORM (Object Relational Mapping) ou Mapeamento de Objeto Relacional se trata de uma técnica na programação que consiste em fazer a ponte entre modelo relacional (banco de dados) e objetos (aplicação) atrevés de frameworks capazes de converter dados entre sistemas utiizando linguagens de programação orientada a objetos.

### O que é? 

Banco de dados relacional | Programção Orientada a Objetos
- Tabela <-> Classe
- Coluna <-> Atributo
- Registro <-> Objeto

*OOP* -> *ORM* -> *RDB*

- OOP/POO = Programação Orientada a Objetos
- ORM = Mapeamento Objeto Relacional
- RBD = Banco de Dados Relacional

## JPA - Java Persistence API

JPA (ou Java Persistence API) é uma especificação oficial que descreve como deve ser o vomportamento dos frameworks de persistência Java que desejarem implement-la.

Ser uma espesificação significa que a JPA não possui código que possa ser executado.

Você pode pensar na espesificação JPA como uma interface que possui algumas assinaturas, mas que precisa que uma classe a implemente.

Implementação é algo que pode ser executado na nossa aplicação.

Qualquer pessoa ou equipe pode escrever a sua própria implementaão JPA.

Dentre as mais famosas temos o `OpenJPA` da Apache, o `Hibernate` da Red Hat e o `EclipseLink` da Eclipse Foundation.

A grande ideia da especificação JPA é que a aplicação possa trocar de implementação sem que precise de mudaças no código. Apenas um pouco de configuração.

### JPA e Hibernate - Exemplos parte 1

#### Annotation JPA

- Entity
- Table
- Column
- Id
- GeneratedValue
- SequenceGenerator
- ManyToOne
- OneToMany
- OneToOne
- MannyYoMany
- JoinColumn
- JoinTable
- OrderColumn
- MapKeyColumn
- ElementCollection

#### Entity, Table, Seq, ID e Column
```
@Entity
@Table (name = "TB_CLIENTE")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cliente_seq")
    @SequenceGenerator(name=2cliente_seq", sequenceName="sq_cliente", initialValue = 1)
    private Long id;
    
    @Column(nmae = "NOME", nullable = false)
    private String nome;
    
    @Column(name = "CPF", nullable = false)
    private Long cpf;
    
    @Column(name = "TEL", nullable = false)
    private Long tel;
    
    @Column(name = "ENDERECO", nullable = false)
    private String endereco;
    
    @Column(name = "NUMERO", nullable = false)
    private Integer numero;
    
    @Column(name = "CIDADE", nullable = false)
    private String cidade;
}
```

#### ElementCollection
```
@Entity
@Table(name="TB_PESSOA")
public class Pessoa {

    @Id
    @Column(name="ID")
    private long id;
    
    @Column(name="name")
    private String nome;
    
    @ElementCollection
    @CollectionTable(name="apelidos"
    @Column(name="apelido_pessoa"
    private Collection<String> apelidos;
}
```

Cria automaticamente a Tabela SQL e faz a conexão entre as tabelas

#### ELementorCollection com objeto

```
@Embeddable
public class Endereco {
    
    @Column(name="rua")
    private String rua;
    
    @Column(name="numero")
    private int numero;
}
```

```
@Entity
@Table (name=TB_PESSOA")
public class Pessoa {
    
    @Id
    @Column(name="ID")
    private long id;
    
    @Column(name="nome")
    private String nome;
    
    @ElementCollection
    @CollectionTable(name="apelido")
    @Column(name="apelidos_pessoa")
    private Collection<String> apelidos;
    
    @ElementCollecton
    @CollectionTable(name="cliente_enderecos")
    private Collection<Endereco> endereco;
}
```

### JPA e Hibernate -Exemplos parte 2

#### OneToMany, ManyToOne e OrderColumn

```
@Entity
@Table(name= "TB_CURSO")
public class Curso {

    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "nome")
    private String nome;
    
    @OneToMany(mappedBy = "curso")
    @OrderColumn(name="order_matricula")
    private List<Matricula> matriculas;
}
```

```
@Entity
@Table(name= "TB_MATRICULA")
public class Matricula {

    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "valor")
    private Doble valor;
    
    @Column(name = "data")
    private Instant data;
    
    @ManyToOne
    private Curso curso;
}
```

#### ManyToMany, JoinTable e JoinColumn

```
@Entity
@Table(name= "TB_EMPREGADO")
public class Empregado {

    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "nome")
    private String nome;
    
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
    name = "TB_EMPREGADO_PROJETO",
    joinColumns = { @JoinColumn(name ="empregado_id") },
    inverseJoinColumns = { @JoinColumn(name = "projeto_id") }
    )
    Set<Projeto> projetos = new Hashset<>();
}
```

```
@Entity
@Table(name= "TB_PROJETO")
public class Projeto {

    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "nome")
    private String nome;
    
    @ManyToMany(mappedBy = "projetos")
    private Set<Empregado> empregados = new HashSet<>();
}
```

#### MapKeyColumn

```
@Entity
@Table(name= "TB_EMPREGADO")
public class Empregado {

    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "nome")
    private String nome;
    
    @ElementCollection
    @CollectionTable(name="TB_EMPREGADOS_TELEFONES")
    @MapKeyColumn(name="tipo_telefone")
    @Column(name="numero_telefone")
    private Map<String, String> numeroTelefones;
}
```