<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <title>Crud Cidades</title>
</head>
<body>
    <div class="container-fluid mt-3">
        <h1 class="text-center">Gerenciamento de Cidades</h1>
    </div>

    <#if cidadeAtual??>
        <form action="/alterar" method="POST" class="container mt-4">
            <input type="hidden" name="_method" value="PATCH"/>
            <input required type="hidden" name="nomeAtual" value="${cidadeAtual.nome}"/>
            <input required type="hidden" name="ufAtual" value="${cidadeAtual.uf}"/>
    <#else>
        <form action="/criar" method="POST" class="container mt-4">
    </#if>

        <div class="form-group">
            <label for="nome">Cidade:</label>
            <input required value="${(cidadeAtual.nome)!}" name="nome" type="text" class="form-control" id="nome" placeholder="Nome da cidade">
        </div>

        <div class="form-group">
            <label for="estado">Estado:</label>
            <input required maxlength="2" value="${(cidadeAtual.uf)!}" name="uf" type="text" class="form-control" id="estado" placeholder="Sigla do estado">
        </div>

        <#if cidadeAtual??>
            <button type="submit" class="btn btn-warning">Concluir Alteração</button>
        <#else>
            <button type="submit" class="btn btn-primary">Adicionar</button>
        </#if>

    </form>

    <table class="table table-striped table-hover mt-3 container">
        <thead class="thead-dark">
            <tr>
                <th>Nome</th>
                <th>Estado</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <#list listarCidades as cidade>
                <tr>
                    <td>${cidade.nome}</td>
                    <td>${cidade.uf}</td>
                    <td>
                        <div class="d-flex justify-content-start">
                            <a href="/preparaAlterar?nome=${cidade.nome}&uf=${cidade.uf}" class="btn btn-sm btn-warning me-3">Editar</a>
                            <a href="/excluir?nome=${cidade.nome}&uf=${cidade.uf}" class="btn btn-sm btn-danger">Excluir</a>
                        </div>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>
</body>
</html>