module org.sahthan.sahthan_v1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires jakarta.cdi;

    // Exporta e abre o novo pacote de inicialização
    exports org.sahthan.sahthan_v1.application;
    opens org.sahthan.sahthan_v1.application to javafx.fxml;

    // Seus pacotes existentes
    exports org.sahthan.sahthan_v1;
    exports org.sahthan.sahthan_v1.controller;
    opens org.sahthan.sahthan_v1.controller to javafx.fxml;
    opens org.sahthan.sahthan_v1.model to org.hibernate.orm.core, javafx.base, javafx.fxml, jakarta.persistence;
    exports org.sahthan.sahthan_v1.controller.aeronave;
    opens org.sahthan.sahthan_v1.controller.aeronave to javafx.fxml;
    exports org.sahthan.sahthan_v1.controller.hangar;
    opens org.sahthan.sahthan_v1.controller.hangar to javafx.fxml;
    exports org.sahthan.sahthan_v1.controller.localidade;
    opens org.sahthan.sahthan_v1.controller.localidade to javafx.fxml;
    exports org.sahthan.sahthan_v1.controller.locacao;
    opens org.sahthan.sahthan_v1.controller.locacao to javafx.fxml;
    exports org.sahthan.sahthan_v1.controller.usuario;
    opens org.sahthan.sahthan_v1.controller.usuario to javafx.fxml;
}