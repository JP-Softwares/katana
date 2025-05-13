# Katana ✂️  
**Sistema de Gerenciamento para Barbearias**

> “A navalha certa pode transformar um simples corte em uma obra-prima; o software certo faz o mesmo com o seu negócio.”

---

## Índice
1. [Introdução](#introdução)  
2. [Motivação & História](#motivação--história)  
3. [Tecnologias Utilizadas](#tecnologias-utilizadas)  
4. [Principais Funcionalidades](#principais-funcionalidades)  

---

## Introdução
Katana é um software completo para barbearias que simplifica **agendamentos**, otimiza o **controle financeiro** e eleva a experiência do cliente. Foi criado pensando na rotina agitada dos barbeiros – da gestão da agenda ao fechamento do caixa – oferecendo tudo em uma única plataforma intuitiva.

---

## Motivação & História
O projeto nasceu da história de **Genival**, pai de um dos desenvolvedores. Babeiro iniciante, ele lutava diariamente para conciliar papeladas de agendamentos, anotações de pagamentos e controle de estoque.  
Frustrado pela falta de ferramentas simples e acessíveis, pediu ajuda ao filho. Assim, um pequeno hack de fim de semana evoluiu para o **Katana**, batizado em homenagem à precisão que uma katana representa – o mesmo nível de precisão que queremos levar à gestão de barbearias.

---

## Tecnologias Utilizadas
| Camada            | Tecnologia | Por que escolhemos? |
|-------------------|------------|---------------------|
| **Frontend**      | **Svelte** | Performance reativa com curva de aprendizado suave, resultando em interfaces rápidas e responsivas. |
| **Backend**       | **Java (Spring Boot)** | Robustez, segurança e amplo ecossistema para integração com bancos, gateways de pagamento e relatórios. |
| **IDE**           | **Visual Studio Code** | Leve, extensível e amigável para times híbridos (JS + Java). |
| **Banco de Dados**| PostgreSQL | Confiável, open-source e ótimo suporte a consultas complexas. |

---

## Principais Funcionalidades
### 1. Agendamentos Inteligentes
- **Agenda visual** com bloqueio automático de horários já reservados.  
- **Fila de espera**: caso haja cancelamento, o próximo cliente na lista é avisado.

### 2. Controle Financeiro
- **Dashboard em tempo real** com receitas, despesas e lucratividade por período.  
- **Lançamentos rápidos** de vendas de produtos (pomadas, shampoos etc.).  
- **Relatórios PDF/CSV** de fluxo de caixa, contas a receber e comissão de barbeiros.  
- **Integração opcional** com maquininhas e PIX para conciliação automática.

### 3. Outras Ferramentas
- Cadastro de serviços & preços personalizáveis.  
- Gestão de barbeiros e metas individuais.  
- Controle básico de estoque de produtos.  

---

## – Fluxo de Trabalho e Governança de Código  

> As regras a seguir garantem que o **Katana** cresça de forma organizada, previsível e auditável.  

---

###  Política de Commits  

| Regra | Detalhe |
|-------|---------|
| **Idioma** | Inglês |
| **Formato** | Linha única, concisa, verbo no imperativo, iniciando em maiúscula.<br>Ex.:<br>`git commit -m "Create home screen"` |
| **Autores** | Se houver mais de um autor, use `--author` ou `-s` (Signed-off-by) para registrar todas as assinaturas.<br>Ex.:<br>`git commit -s -m "Refactor booking service"` |

---

### Política de Branches  

| Branch | Propósito | Pode receber commits diretos? |
|--------|-----------|-------------------------------|
| **main** | Versão estável em produção | ❌ |
| **develop** | Integração contínua / próxima release | ❌ |

1. Todo desenvolvimento parte de **develop**.  
2. Nunca faça `push` direto em **main** ou **develop**; abra um **Pull Request**.  
3. O PR é aceito se:  
   - *Build* do **GitHub Actions** estiver verde (backend + frontend + testes + lints).  
   - Análise estática do **SonarCloud** atingir cobertura & qualidade acordadas.  
   - For revisado por **pelo menos um dev** que não seja o autor.

---

### Padrões para Criação de Branches (GitFlow)  

| Tipo | Prefixo | Exemplo |
|------|---------|---------|
| **Caso de Uso** | `feature/UC_` | `feature/UC_RegisterCustomer` |
| **História de Usuário** | `feature/US_` | `feature/US_BarberEarnCommission` |
| **Correção / Configuração** | `fix/` | `fix/FIX_DockerCompose` |
| **Issue Genérica** | `issue/NN_` | `issue/08_FixConnectionWithDatabase` |

Todas devem derivar de **develop**.

---

### Política de Aprovação  

1. **CI obrigatório**: o PR é bloqueado se qualquer *job* falhar.  
2. **Code review obrigatório**: outro membro da equipe deve aprovar.  
3. **Padrões de estilo**: Prettier (frontend) e Spotless (backend) são executados na pipeline – quebras rejeitam o PR.  

---

### Versionamento de Código  

Utilizamos **Semantic Versioning 2.0.0**:  

MAJOR.MINOR.PATCH

yaml
Copiar
Editar

| Dígito | Quando incrementar |
|--------|--------------------|
| **MAJOR** | Mudanças incompatíveis de API |
| **MINOR** | Novas funcionalidades retro-compatíveis |
| **PATCH** | Correções retro-compatíveis |

Versões são marcadas com tags Git: `v1.4.2`.

---

## – Repositório de Documentação  

- Toda documentação viva reside na **Wiki do GitHub**.  
- Commits seguem as mesmas regras da Seção 4.1.  
- Documentos permanecem na branch **main** da Wiki; não há ramificações paralelas.

###  Versionamento de Documentos  

MAJOR.MINOR

yaml
Copiar
Editar

| Alteração | Ação |
|-----------|------|
| Ajuste menor / revisão ortográfica | `+MINOR` |
| Conteúdo novo significativo | `+MAJOR` |

---

## – Monitoramento & Governança  

A **equipe de gestão do Katana** supervisiona o cumprimento destas normas. Ela pode:  

- Vetar ou excluir conteúdo fora do padrão.  
- Auxiliar novos contribuidores.  
