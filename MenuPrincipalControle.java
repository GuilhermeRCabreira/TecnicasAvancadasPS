public class MenuPrincipalControle implements ActionListener {

    private JanelaPrincipal jp;
    private PedidosInternos ped;
    private RedeSocial rede;
    private SolicitacaoFoto foto;
    private Usuario usuario;
    private PedidosInternosDAO pedDAO;
    private UsuarioDao usuDAO;

    public MenuPrincipalControle(JanelaPrincipal jp, Usuario usuario, PedidosInternos ped, SolicitacaoFoto foto,
            RedeSocial rede) {
        super();
        this.jp = jp;
        this.usuario = usuario;
        this.ped = ped;
        this.foto = foto;
        this.rede = rede;
        this.jp.getTaunt().getButtonAutenticar().addActionListener(this);
        this.jp.getTaunt().getButtonCancelar().addActionListener(this);
        this.jp.getTrede().getButtonEnviar().addActionListener(this);
        this.jp.getTrede().getButtonCancelar().addActionListener(this);
        this.jp.getTfoto().getButtonEnviar().addActionListener(this);
        this.jp.getTfoto().getButtonCancelar().addActionListener(this);
        this.jp.getTfoto().getRadioButtonCobertura().addActionListener(this);
        this.jp.getTfoto().getRadioButtonLocalizacao().addActionListener(this);
        this.jp.getMenuAscom().addActionListener(this);
        this.jp.getMenuItemCadastroRede().addActionListener(this);
        this.jp.getMenuItemSair().addActionListener(this);
        this.jp.getMenuItemSolicitacao().addActionListener(this);
        this.jp.getMenuItemAutenticar().addActionListener(this);

        pedDAO = new PedidosInternosDAO();
        usuDAO = new UsuarioDao();

    }

    public void consultaUsuario() {
        String id = jp.getTaunt().getFieldUsuario().getText();
        String senhaAcesso = jp.getTaunt().getFieldSenha().getText();

        usuario.setId(id);
        usuario.setSenhaAcesso(senhaAcesso);

        if (u.verificaEspV().size() > 0) {
            System.out.println("Campos " + usuario.verificaEspV() + "estão em branco!");
        } else {
            if (usuDAO.consultaUsuario(u)) {
                this.jp.getMenuAscom().setEnabled(true);
            } else {
                System.out.println("Usuario ou senha não encontrado!");
            }
        }
    }

    public void cadastraRede() {
        String orgao = this.jp.getTrede().getFieldOrgao().getText();
        String responsavel = this.jp.getTrede().getFieldResponsavel().getText();
        String email = this.jp.getTrede().getFieldEmail().getText();
        String instagram = this.jp.getTrede().getFieldInstagram().getText();
        String facebook = this.jp.getTrede().getFieldFacebook().getText();
        String twitter = this.jp.getTrede().getFieldTwitter().getText();
        String youtube = this.jp.getTrede().getFieldYouTube().getText();
        String linkedin = this.jp.getTrede().getFieldLinkedIn().getText();
        String outra = this.jp.getTrede().getFieldOutra().getText();

        rede.setOrgao(orgao);
        rede.setResponsavel(responsavel);
        rede.setEmail(email);
        rede.setInstagram(instagram);
        rede.setFacebook(facebook);
        rede.setTwitter(twitter);
        rede.setYoutube(youtube);
        rede.setLinkedin(linkedin);
        rede.setOutra(outra);

        if (rede.verificaEspV().size() > 0) {
            System.out.println("Campos " + rede.verificaEspV() + "estão em branco!");
        } else {
            if (pedDAO.solicitaFoto(foto)) {
                System.out.println("Solicitação realizada com sucesso");
                this.jp.getTfoto().limpaTela();
            } else {
                System.out.println("Falha ao solicitar!");
                this.jp.getTfoto().limpaTela();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if (e.getActionCommand().equals("Menu Autenticar")) {
            jp.puxaTelaAutenticacao();
        }
        if (e.getActionCommand().equals("Sair")) {
            jp.dispose();
        }
        if (e.getActionCommand().equals("Cadastro de rede social afiliada à UFCSPA")) {
            jp.puxaTelaRede();
        }
        if (e.getActionCommand().equals("Solicitação de cobertura fotográfica ou localização de imagens")) {
            jp.puxaTelaFoto();
        }
        if (e.getActionCommand().equals("Autenticar")) {

            consultaUsuario();

        }
        if (e.getActionCommand().equals("Cancelar")) {

            jp.getTrede().limpaTela();
            jp.getTaunt().limpaTela();
            jp.getTfoto().limpaTela();
            jp.puxaTelaInicio();

        }
        if (e.getActionCommand().equals("Enviar")) {
            cadastraRede();
            solicitaFoto();
            jp.getTrede().limpaTela();
            jp.getTfoto().limpaTela();
        }
    }

}
