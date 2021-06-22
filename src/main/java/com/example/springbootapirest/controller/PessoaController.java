package com.example.springbootapirest.controller;

import com.example.springbootapirest.controller.DTO.PessoaRq;
import com.example.springbootapirest.controller.DTO.PessoaRs;
import com.example.springbootapirest.model.Pessoa;
import com.example.springbootapirest.repository.PessoaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private PessoaRepository pessoaRepository;

    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @GetMapping("/")
    public List<PessoaRs> findAll(){
        var pessoas = pessoaRepository.findAll();
        return pessoas.stream().map(PessoaRs::converter).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
        public PessoaRs findByid(@PathVariable("id") Long id){
            var pessoa = pessoaRepository.getOne(id);
            return PessoaRs.converter(pessoa);
        }
    @PostMapping("/")
    public void  savePerson(@RequestBody PessoaRs pessoa){
        var p = new Pessoa();
        p.setNome(pessoa.getNome());
        p.setSobrenome(pessoa.getSobrenome());
        pessoaRepository.save(p);
    }

    @PutMapping("/{id}")
    public void updatePerson(@PathVariable("id") Long id,@RequestBody PessoaRs pessoa) throws Exception {
        var p = pessoaRepository.findById(id);
        if(p.isPresent()){
            var pessoaSave = p.get();
            pessoaSave.setNome(pessoa.getNome());
            pessoaSave.setSobrenome(pessoa.getSobrenome());

            pessoaRepository.save(pessoaSave);
        }else{
            throw new Exception("Pessoa nao encontrada");
        }

    }
}
