package com.jpsoftwares.katana.service;

import com.jpsoftwares.katana.modelo.Produto;
import com.jpsoftwares.katana.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Método para listar todos os produtos
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    // Método para buscar produto por ID
    public Produto findById(Long id) {
        return produtoRepository.findById(id)
                .orElse(null);
    }

    // Método para criar um novo produto
    public Produto create(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Método para atualizar um produto existente
    public Produto update(Produto produto) {
        if (produto.getId() == null || !produtoRepository.existsById(produto.getId())) {
            return null;
        }
        return produtoRepository.save(produto);
    }

    // Método para deletar um produto por ID
    public boolean delete(Long id) {
        if (!produtoRepository.existsById(id)) {
            return false;
        }
        produtoRepository.deleteById(id);
        return true;
    }
}

