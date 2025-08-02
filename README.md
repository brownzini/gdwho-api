# Projeto GDWho - API

    API para o projeto GDWho. Desenvolvida para manter a comunicação entre o sistema, o modelo de machine learning e o usuário.

### Estrutura do Projeto

O projeto segue a arquitetura **Clean Architecture** e implementa as regras de negócio estabelecidas.

```
└── com/gdwho/api/
    ├── application/        # Camada de Aplicação:
    │     └── gateways/          # Responsável pelo desacoplamento e conexão com camadas externas
    │     └── usecases/          # Responsável por orquestrar os casos de uso
    ├── domain/             # Camada de Domínio:
    │     └── entities/          # Responsável pela abstração das regras de negócio
    ├── infrastructure/     # Camada de Infraestrutura:
    │     └── controllers/    # Camada de API
    │     └── filter/                # Serviço de filtragem de requisições (rate limit)
    │     └── handler/        # Camada de captura e tratamento centralizado de exceções
    │     └── persistence/    # Camada de persistência:
    │     └── gateways/       # Camada de Implementação: 
    │         └── */                 # Usadas na inversão de dependências
    └── config/             # Camada de Inicialização: 
        └── */                 # Gerenciamento do Spring e injeção de dependências
```

### Documentação da API (Endpoints)

A API está disponível no base path `/api/v1`.

#### Endpoints de `Users` (Requer BEARER TOKEN | Roles permitidas: `[ADMIN]`)

| Verbo HTTP | Rota     | Descrição                                |
| ---------- | -------- | ---------------------------------------- |
| `GET`      | `/users` | Lista, filtra e pagina todos os usuarios |

**Requisição:**

http://localhost:8080/v1/api/users

Resposta

```
[
    {
        "id": 1,
        "username": "master",
        "password": "$2a$10$EgaEA06/7BOmfVSvTOq8K.cEIO89U0AKMmQ0vwKvcT3xNZvqpdyZm",
        "role": "ADMIN",
        "createdAt": "2025-08-02T14:46:49.091599Z",
        "dataList": [
            {
                "id": 1,
                "value": "conteudo teste de db"
            }
        ],
        "entries": [
            {
                "id": 1,
                "input": "meu dado de input",
                "output": "calma o nada",
                "label": 1.0
            }
        ]
    },
    {
        "id": 2,
        "username": "jeguelson",
        "password": "$2a$10$EgaEA06/7BOmfVSvTOq8K.cEIO89U0AKMmQ0vwKvcT3xNZvqpdyZm",
        "role": "USER",
        "createdAt": "2025-08-02T14:46:49.096917Z",
        "dataList": [
            {
                "id": 2,
                "value": "teste de data para user 2"
            }
        ],
        "entries": [
            {
                "id": 2,
                "input": "testando um teste",
                "output": "um teste testado",
                "label": 1.0
            }
        ]
    }
]
```

#### Endpoints de `Auth`( Não requer BEARER TOKEN )

| Verbo HTTP | Rota                       | Descrição                                 |
| ---------- | -------------------------- | ----------------------------------------- |
| `POST`     | `/auth/login`              | Realiza o login do usuário                | 
| `POST`     | `/auth/register`           | Cadastra novo usuário (role padrão USER)  |

**Requisição**

```
http://localhost:8080/v1/api/auth
```

#### Endpoints de `Game` ( Requer BEARER TOKEN | roles permitidas = [ADMIN|USER] )

| Verbo HTTP | Rota                       | Descrição                                 |
| ---------- | -------------------------- | ----------------------------------------- |
| `GET`      | `/game/guess`              | Envia input para modelo retornar resposta |
| `POST`     | `/game/create`             | Cria o jogo treinando o modelo            |
| `PUT`      | `/game/update/data/{id}`   | Atualização de input da lista de dados    |
| `PATCH`    | `/game/update/entrie/{id}` | Atualização de entradas para treinamento  |
| `DELETE`   | `/game/delete/data/{id}`   | Remoção do input da lista de dados        |
| `DELETE`   | `/game/delete/entrie/{id}` | Remoção da entrada para treinamentos      |

**Requisição**

```
http://localhost:8080/v1/api/game
```

### Segurança

    * Autenticação com JWT * 
    * Sistema de permissões, onde o usuário comum só pode persistir dados próprios * 
    * Limitação de requisições por IP/usuário *

### Consumo de serviço externo (MODEL API)

    * Passagem de entradas e lista de dados
    * Cacheamento de resultados com REDIS
    * Possível aumento no tempo de resposta devido ao processo de treinamento


#### Fluxo da aplicação
![Image](https://res.cloudinary.com/duthrgtp7/image/upload/v1754152315/fluxodeservico_galic4.png)