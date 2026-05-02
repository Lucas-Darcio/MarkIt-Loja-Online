# MarkIt-Loja-Online

Uma loja em que você pode facilmente marcar os produtos que quer na sua lista de desejos.
Eu já desenvolvi uma loja online, mas usando Node, express, prisma e Typescript para o backend, o objetivo desse novo projeto é fazer o Backend usando Java e Spring Boot aliada a várias outras bibliotecas, o Frontend será feito em React com tailwind.
O objetivo principal desse meu projeto pessoal é atualizar meu conhecimento em programação em Java, e aprender a usar o spring boot.
# Plan

- ~~Github~~
- Container(s) docker (em progresso)
  - ~~Banco de dados~~
  - ~~Backend~~
  - frontend
- Backend API (em progresso)
- Banco de dados (em progresso)
- Frontend (Futuro)
- Continues Integration (Futuro)
- Continuous deploy (Futuro)

# Dev deppendences:
## Backend;
Usando Java 21.0

- Spring Web
- Spring Data JPA
- Spring Security
- Spring Boot DevTools
- Validation
- Lombok

# Docker
Estou usando o seguinte compose na pasta mais externa, por enquanto ele é responsável pela criação dos serviços do Backend e Banco de dados e volumes para o banco de dados.
[Compose](compose.yaml)

E o seguinte Dockerfile no diretório do Backend:
[Dockerfile](Backend/Dockerfile)

# Banco de dados
O SGBD sendo usado é o MySQL, e como o projeto é baseado em uma implementação já realizada em Node e typescript, as relações do banco já estão bem definidas, mas no formato prisma a seguir, o qual estou adaptando para o formato do spring API.
```
model Product {
  id            String         @id @default(uuid()) @db.Char(36)
  name          String         @unique @db.VarChar(100)
  description   String         @db.Text
  price         Decimal        @db.Decimal(10, 2)
  stock         Int            @db.Int()
  createdAt     DateTime       @default(now())
  updatedAt     DateTime       @updatedAt
  purchaseItems PurchaseItem[]

  @@index([name])
}

model User {
  id         String     @id @default(uuid()) @db.Char(36)
  name       String     @db.VarChar(100)
  email      String     @unique @db.VarChar(100)
  password   String     @db.Char(60)
  userTypeId String     @db.Char(36)
  userType   UserType   @relation(fields: [userTypeId], references: [id])
  createdAt  DateTime   @default(now())
  updatedAt  DateTime   @updatedAt
  purchases  Purchase[]
}

model UserType {
  id    String @id @default(uuid()) @db.Char(36)
  label String @db.VarChar(10)
  User  User[]
}

model Purchase {
  id            String         @id @default(uuid()) @db.Char(36)
  userId        String         @db.Char(36)
  user          User           @relation(fields: [userId], references: [id])
  status        Int            @db.TinyInt
  createdAt     DateTime       @default(now())
  updatedAt     DateTime       @updatedAt
  purchaseItems PurchaseItem[]
}

model PurchaseItem {
  id         String   @id @default(uuid()) @db.Char(36)
  purchaseId String   @db.Char(36)
  purchase   Purchase @relation(fields: [purchaseId], references: [id])
  productId  String   @db.Char(36)
  product    Product  @relation(fields: [productId], references: [id])
  quantity   Int      @db.Int()
  createdAt  DateTime @default(now())
  updatedAt  DateTime @updatedAt
}
```

