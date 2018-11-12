package br.edu.utfpr;

import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.model.EmprestimoLiterario;
import br.edu.utfpr.model.LivroLiterario;
import br.edu.utfpr.model.service.AlunoService;
import br.edu.utfpr.model.service.EmprestimoLiterarioService;
import br.edu.utfpr.model.service.LivroLiterarioService;
import br.edu.utfpr.query.EmprestimoAluno;
import br.edu.utfpr.util.MessageUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@SessionScoped
public class EmprestimoLiterarioBean implements Serializable {

    private Long alunoId;
    private Long livroLiterarioId;
    //serve para recuperar o emprestimo da página de adição de livros
    private Long emprestimoId;
    private EmprestimoLiterario emprestimoLiterario;
    private List<EmprestimoLiterario> emprestimoLiterarioList;
    private EmprestimoLiterarioService emprestimoLiterarioService;
    private AlunoService alunoService;
    private LivroLiterarioService livroLiterarioService;

    private void reset() {
        this.emprestimoLiterario = new EmprestimoLiterario();
        this.alunoId = null;
        this.emprestimoId = null;
        this.livroLiterarioId = null;
    }

    public String voltar() {
        reset();
        return "emprestimo-literario?faces-redirect=true";
    }

    public EmprestimoLiterarioBean() {

    }

    @PostConstruct
    public void init() {
        emprestimoLiterario = new EmprestimoLiterario();
        emprestimoLiterarioService = new EmprestimoLiterarioService();
        alunoService = new AlunoService();
        livroLiterarioService = new LivroLiterarioService();
        findAll();
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getLivroLiterarioId() {
        return livroLiterarioId;
    }

    public void setLivroLiterarioId(Long livroLiterarioId) {
        this.livroLiterarioId = livroLiterarioId;
    }

    public EmprestimoLiterario getEmprestimoLiterario() {
        return emprestimoLiterario;
    }

    public void setEmprestimoLiterario(EmprestimoLiterario emprestimoLiterario) {
        this.emprestimoLiterario = emprestimoLiterario;
    }

    public List<EmprestimoLiterario> getEmprestimoLiterarioList() {
        return emprestimoLiterarioList;
    }

    public void setEmprestimoLiterarioList(List<EmprestimoLiterario> emprestimoLiterarioList) {
        this.emprestimoLiterarioList = emprestimoLiterarioList;
    }

    public EmprestimoLiterarioService getEmprestimoLivroLiterarioService() {
        return emprestimoLiterarioService;
    }

    public void edit(EmprestimoLiterario emprestimoLiterario) {
        this.emprestimoLiterario = emprestimoLiterario;
    }

    public Long getEmprestimoId() {
        return emprestimoId;
    }

    public void setEmprestimoId(Long emprestimoId) {
        this.emprestimoId = emprestimoId;
    }

    public void delete(EmprestimoLiterario emprestimoLiterario) {
        //System.out.println("Id " + emprestimoLivroLiterario.getId());
        EmprestimoLiterario emprestimoLiterarioOriginal = emprestimoLiterarioService.getById(emprestimoLiterario.getId());
        //System.out.println("Turma: " + emprestimoLivroLiterarioOriginal);
        boolean isSuccess = emprestimoLiterarioService.delete(emprestimoLiterarioOriginal);
        System.out.println("Deletado " + isSuccess);
        if (isSuccess) {
            findAll();
            //System.out.println("findall executado ");
            MessageUtil.showMessage("Removido com sucesso", "", FacesMessage.SEVERITY_INFO);
        }
        this.emprestimoLiterario = new EmprestimoLiterario();
    }

    public void deleteLivroEmprestimo(LivroLiterario livroLiterario) {

        livroLiterario = livroLiterarioService.getById(livroLiterario.getId());
        livroLiterario.setDisponibilidade("T");
        livroLiterarioService.update(livroLiterario);

        EmprestimoLiterario emprestimoLiterario = emprestimoLiterarioService.getById(emprestimoId);
        System.out.println("Antes " + livroLiterario.getTitulo());

        int index = -1;
        for (int i = 0; i < emprestimoLiterario.getLivroLiterarios().size(); i++) {
            LivroLiterario l = emprestimoLiterario.getLivroLiterarios().get(i);
            if (l.getId() == livroLiterario.getId()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            emprestimoLiterario.getLivroLiterarios().remove(index);
        }

        System.out.println("Depois");
        for (LivroLiterario l : emprestimoLiterario.getLivroLiterarios()) {
            System.out.println("Livro " + l.getTitulo());
        }

        emprestimoLiterarioService.update(emprestimoLiterario);

        MessageUtil.showMessage("Livro removido com sucesso", "", FacesMessage.SEVERITY_INFO);

    }

    public String persist() {

        if (emprestimoLiterario.getId() == null) {
            Aluno aluno = alunoService.getById(alunoId);

            emprestimoLiterario.setAluno(aluno);

            emprestimoLiterarioService.save(emprestimoLiterario);
            this.emprestimoId = emprestimoLiterario.getId();
            MessageUtil.showMessage("Persistido com sucesso", "", FacesMessage.SEVERITY_INFO);
            this.emprestimoLiterario = new EmprestimoLiterario();
            findAll();
            return redirectToAddBooks();

        } else {
            Aluno aluno = alunoService.getById(alunoId);
            LivroLiterario livroLiterario = livroLiterarioService.getById(livroLiterarioId);
            EmprestimoLiterario emprestimoLiterarioOriginal = emprestimoLiterarioService.getById(emprestimoLiterario.getId());
            emprestimoLiterarioOriginal.setDataEmprestimo(emprestimoLiterario.getDataEmprestimo());
            emprestimoLiterarioOriginal.setDataDevolucao(emprestimoLiterario.getDataDevolucao());
            emprestimoLiterarioOriginal.setAluno(aluno);
            emprestimoLiterario.getLivroLiterarios().add(livroLiterario);
            emprestimoLiterarioService.update(emprestimoLiterarioOriginal);
            findAll();

            reset();
        }

        return "";
    }

    public void devolver() {

        //busca pelo livro para devolução e muda o status para devolvido
        LivroLiterario livroLiterario = livroLiterarioService.getById(livroLiterarioId);
        livroLiterario.setDisponibilidade("T");
        livroLiterario = livroLiterarioService.update(livroLiterario);

        //pega o emprestimo relacionado ao livro e este livro está atrelado ao aluno corrente que chegou para devolver
        //ESSE EMPRESTIMO É O ULTIMO?
        EmprestimoLiterario el = emprestimoLiterarioService.getEmprestimoByLivro(livroLiterarioId);
        //pega os livros do emprestimo já atualizado com a devolução corrente
        List<LivroLiterario> livros = el.getLivroLiterarios();
        //conta quantos livros do emprestimo já estão devolvidos
        //PROBLEMA ALUNO A devolve um dos 3 livros do emprestimo e ainda continua devendo todos livros
        //do emprestimo
        //EM Seguida outro ALUNO B empresta o livro que o aluno A devolveu
        //ALUNO A e ALUNO B estão devendo o mesmo livro no mesmo momento
        int counter = 0;
        for (LivroLiterario l : livros) {
            if (l.getDisponibilidade().equals("T")) {
                counter++;
            } else {
                //é estado F
                //verifica se o livro que está em estado F pertence ao aluno corrente ou a outro
                EmprestimoLiterario emprestimoLiterario = emprestimoLiterarioService.getEmprestimoByLivro(l.getId());
                Long idAluno = emprestimoLiterario.getAluno().getId();

                //emprestado para outro aluno. Portanto, foi devolvido.
                if (el.getAluno().getId() != idAluno) {
                    counter++;
                }
            }
        }
        //muda o status do emprestimo para close quando todos os livros forem devolvidos
        if (counter == el.getLivroLiterarios().size()) {
            el.setStatus(EmprestimoLiterario.Status.C);
            //update
            emprestimoLiterarioService.update(el);
        }

        /*
        //No emprestimo do aluno pode ter livros emprestados e devolvidos
        if (counter != 0 && counter < el.getLivroLiterarios().size()) {
            el.setStatus(EmprestimoLiterario.Status.P);

            emprestimoLiterarioService.update(el);
        }
         */
        System.out.println("Livro devolvido " + livroLiterario.getTitulo() + " Status :" + livroLiterario.getDisponibilidade());

    }

    public void adicionarLivroEmprestimo() {
        //livro
        //emprestimo
        this.emprestimoLiterario = emprestimoLiterarioService.getById(emprestimoId);
        if (this.emprestimoLiterario.getLivroLiterarios().size() < 4) {

            LivroLiterario livroLiterario = livroLiterarioService.getById(livroLiterarioId);
            livroLiterario.setDisponibilidade("F");
            livroLiterario = livroLiterarioService.update(livroLiterario);

            this.emprestimoLiterario.getLivroLiterarios().add(livroLiterario);
            emprestimoLiterarioService.update(emprestimoLiterario);
            MessageUtil.showMessage("Livro adicionado com sucesso", "", FacesMessage.SEVERITY_INFO);
        } else {
            MessageUtil.showMessage("Você pode adicionar no máximo 4 livros", "", FacesMessage.SEVERITY_ERROR);
        }
    }

    public String finalizarAdicaoLivros() {
        reset();
        return "emprestimo-literario?faces-redirect=true";
    }

    public List<LivroLiterario> listarLivrosEmprestimo() {

        //emprestimo
        this.emprestimoLiterario = emprestimoLiterarioService.getById(emprestimoId);
        return this.emprestimoLiterario.getLivroLiterarios();
    }

    public String redirectToAddBooks() {
        return "add-livros-literarios";
    }

    public List<EmprestimoLiterario> findAll() {
        //System.out.println("************************ findallllllllllllllllllll");
        this.emprestimoLiterarioList = emprestimoLiterarioService.findAllOrderedByIdDesc();
        //System.out.println("Tamanho " + emprestimoLiterarioList.size());
        return emprestimoLiterarioList;
    }

    public List<EmprestimoLiterario> findLivrosEmprestados() {
        //System.out.println("************************ findallllllllllllllllllll");
        this.emprestimoLiterarioList = emprestimoLiterarioService.findAllOrderedByIdDesc();
        //System.out.println("Tamanho " + emprestimoLiterarioList.size());
        return emprestimoLiterarioList;
    }

    public List<EmprestimoAluno> findLivrosCorrentesEmprestados() {
        return emprestimoLiterarioService.findLivrosEmprestados();
    }

}
