package com.application.views.main;

import com.application.navigationbar.MainMenu;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.shared.util.SharedUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.annotation.security.PermitAll;

import org.vaadin.olli.FileDownloadWrapper;

@PermitAll
@PageTitle("Csv Import Grid")
@Route(value="GridCsvImport", layout = MainMenu.class)

public class GridCsvImport extends VerticalLayout {
  
  private Grid<String[]> grid = new Grid<>();

   public GridCsvImport() {
      MemoryBuffer buffer = new MemoryBuffer();
      Upload upload = new Upload(buffer);
      upload.setAcceptedFileTypes(".csv");
      upload.addSucceededListener(e -> {
          displayCsv(buffer.getInputStream());
      });
      Button button = new Button("Download Upload Template");
      FileDownloadWrapper buttonWrapper = new FileDownloadWrapper(
              new StreamResource(
                      "CsvUploadTemplate.csv", () -> getClass().getResourceAsStream("/META-INF/resources/documents/CsvUploadTemplate.csv")));
      buttonWrapper.wrapComponent(button);
      
      add(upload, buttonWrapper, grid);
  }

   private void displayCsv(InputStream resourceAsStream) {
      // Change the separator if needed to something else (for example, to ',').
      CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
      CSVReader reader =
              new CSVReaderBuilder(new InputStreamReader(resourceAsStream)).withCSVParser(parser).build();
      try {
          List<String[]> entries = reader.readAll();
          String[] headers = entries.get(0);
          grid.removeAllColumns();

          for (int i = 0; i < headers.length; i++) {
              int colIndex = i;
              grid.addColumn(row -> row[colIndex])
                      .setHeader(SharedUtil.camelCaseToHumanFriendly(headers[colIndex]));
          }

           grid.setItems(entries.subList(1, entries.size()));
      } catch (IOException | CsvException e) {
          e.printStackTrace();
      }
      
      
  }
}