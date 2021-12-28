# Casos de uso

## User
/users

### Usuário se cadastra
POST /
[Recebe]:
> username*
> password*
> email
> name
> bio

### Usuário logado deve conseguir buscar todos os seus dados
GET /
[Recebe]:

### Usuário logado deve conseguir ajustar suas informações
PUT /
[Recebe]:
> username
> password
> email
> name
> bio

## Profile

### Usuário logado busca todos profiles
GET /
[Recebe]:

### Usuário logado busca um profile
GET /:userId
[Recebe]:

## Band
/bands

### Usuário logado cria banda
POST /
[Recebe]:
> name*
> description

### Usuário logado lista bandas que faz parte
GET /
[Recebe]:

### Usuário logado recupera uma banda que faz parte
GET /:bandId
[Recebe]:

### Usuário logado edita banda que faz parte
PUT /:bandId
[Recebe]:
> name
> description

### Usuário deleta uma banda
DEL /:bandId
[Recebe]:

### Usuário logado adiciona membro em banda que faz parte
POST /:bandId/add-member/:userId
[Recebe]:

### Usuário logado remove membro em banda que faz parte
POST /:bandId/remove-member/:userId
[Recebe]:

## Music
/bands/:bandId/musics

### Usuário logado cria música na banda que faz parte
POST /
[Recebe]:
> name*
> artist*
> tone
> isKnown
> links
> tags

### Usuário logado lista músicas por banda que faz parte
GET /
[Recebe]:

### Usuário logado recupera uma música na banda que faz parte
GET /:musicId
[Recebe]:

### Usuário logado atualiza uma música na banda que faz parte
PUT /:musicId
[Recebe]:
> name
> artist
> tone
> isKnown
> countPlays
> links

### Usuário deleta uma musica que faz parte
DEL /:musicId
[Recebe]:

### Usuário logado favorita música da banda que faz parte
POST /:musicId/favorite
[Recebe]:

### Usuário logado desfavorita música da banda que faz parte
POST /:musicId/unfavorite
[Recebe]:

### Usuário logado marca música como tocada na banda que faz parte
POST /:musicId/known
[Recebe]:

### Usuário logado marca música como não tocada na banda que faz parte
POST /:musicId/undone
[Recebe]:

### Usuário logado busca músicas da banda que faz parte por tag
GET /tags/:tagName
[Recebe]:


------------

## Setlist
/bands/:bandId/setlists

### Usuário logado cria setlist em banda que faz parte
POST /
[Recebe]:
> eventDate*
> name

### Usuário logado recupera setlists em banda que faz parte
GET /
[Recebe]:

### Usuário logado recupera uma setlist em banda que faz parte
GET /:setlistId
[Recebe]:

### Usuário logado atualiza setlist em banda que faz parte
PUT /:setlistId
[Recebe]:
### Usuário logado deleta uma setlist da banda que faz parte
DEL /:setlistId
[Recebe]:
### Usuário logado favorita setlist em banda que faz parte
POST /:setlistId/favorite
[Recebe]:
### Usuário logado desfavorita setlist em banda que faz parte
POST /:setlistId/unfavorite
[Recebe]:
### Usuário logado pode confirmar que setlist foi tocada em banda que faz parte
POST /:setlistId/done
[Recebe]:
### Usuário logado pode retirar confirmação de que setlist foi tocada em banda que faz parte
POST /:setlistId/undone
[Recebe]:
### Usuário logado adiciona uma versão à setlist da banda que faz parte
POST /:setlistId/add-versions/:versionId
[Recebe]:
### Usuário logado remove uma versão à setlist da banda que faz parte
POST /:setlistId/remove-versions/:versionId
[Recebe]:

## Version
/bands/:bandId/versions

### Usuário logado cria uma versão de uma música de banda que faz parte
POST /
[Recebe]:
### Usuário logado recupera lista de versões de banda que faz parte
GET /
[Recebe]:
### Usuário logado recupera uma versão da banda que faz parte
GET /:versionId
[Recebe]:
### Usuário logado edita uma versão da banda que faz parte
PUT /:versionId
[Recebe]:
### Usuário logado deleta uma versão da banda que faz parte
DEL /:versionId
[Recebe]:

## Tag
/bands/:bandId/musics/:musicId/tags

### Usuário logado cria uma tag para música de banda que faz parte
POST /
[Recebe]:
### Usuário logado recupera lista de tags para música de banda que faz parte
GET /
[Recebe]:
### Usuário logado edita tag para música de banda que faz parte
PUT /:tagId
[Recebe]:
### Usuário logado remove tag para música de banda que faz parte
DEL /:tagId
[Recebe]:

## Auth
/auth

### Usuário se loga com username/email e senha
POST /login
[Recebe]:
