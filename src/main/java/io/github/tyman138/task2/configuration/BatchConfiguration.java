package io.github.tyman138.task2.configuration;

import io.github.tyman138.task2.entity.Car;
import io.github.tyman138.task2.entity.DataBaseInfoFile;
import io.github.tyman138.task2.fileReader.CustomFileReader;
import io.github.tyman138.task2.parser.Parser;
import io.github.tyman138.task2.processor.FileProcessor;
import io.github.tyman138.task2.service.CarService;
import io.github.tyman138.task2.service.FileService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private CarService carService;

    @Autowired
    private FileService fileService;

    @Bean
    public ItemReader<Car> carItemReader() {
        List<DataBaseInfoFile> fileList = fileService.findAllByStatusLoaded();
        List<Car> carList = new ArrayList<>();
        fileList.forEach(file -> carList.addAll(
                new Parser().ParseList(
                        new CustomFileReader().readFileFromLocal(
                                file.getPath() + file.getName()))));

        return () -> {
            if (!carList.isEmpty()) {
                Car nextCar = carList.get(0);
                carList.remove(nextCar);
                return nextCar;
            }
            return null;
        };
    }

    @Bean
    public ItemWriter<Car> carItemWriter() {
        return items -> carService.saveAll((List<Car>) items);
    }

    @Bean
    public ItemReader<DataBaseInfoFile> dataBaseInfoFileReader() {
        List<DataBaseInfoFile> fileList = fileService.findAllByStatusLoaded();
        return () -> {
            if (!fileList.isEmpty()) {
                DataBaseInfoFile nextFile = fileList.get(0);
                fileList.remove(nextFile);
                return nextFile;
            }
            return null;
        };
    }

    @Bean
    public ItemProcessor<DataBaseInfoFile, DataBaseInfoFile> processor() {
        return new FileProcessor();
    }

    @Bean
    public ItemWriter<DataBaseInfoFile> dataBaseInfoFileItemWriter() {
        return items -> fileService.saveAllDataBaseInfoFile((List<DataBaseInfoFile>) items);
    }


    @Bean
    public Step saveCarStep(ItemReader<Car> carItemReader, ItemWriter<Car> carItemWriter) {
        return stepBuilderFactory.get("saveCarStep")
                .<Car, Car>chunk(2)
                .reader(carItemReader)
                .writer(carItemWriter)
                .build();

    }

    @Bean
    public Step updateFileStep(ItemReader<DataBaseInfoFile> fileItemReader,
                               ItemProcessor<DataBaseInfoFile, DataBaseInfoFile> processor,
                               ItemWriter<DataBaseInfoFile> dataBaseInfoFileItemWriter
    ) {
        return stepBuilderFactory.get("updateFileStep")
                .<DataBaseInfoFile, DataBaseInfoFile>chunk(5)
                .reader(fileItemReader)
                .processor(processor)
                .writer(dataBaseInfoFileItemWriter)
                .build();
    }

    @Bean
    public Job job(Step saveCarStep, Step updateFileStep) {
        return jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .flow(saveCarStep)
                .next(updateFileStep)
                .end()
                .build();
    }

}

