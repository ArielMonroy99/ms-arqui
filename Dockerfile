FROM openjdk:11.0.16-slim
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ENV HOST $HOST
ENV PORT $PORT
ENV DATABASE $DATABASE
ENV USER $USER
ENV PASSWORD $PASSWORD
ENV JWT_SECRET $JWT_SECRET
ENV JWT_EXPIRATION $JWT_EXPIRATION
ENTRYPOINT ["./wait-for-it.sh", "postgresql:5432 -t0","--",\
            "java", \
            "-cp",                 \
            "app:app/lib/*",                 \
            "com/arqui/vetstore/VetstoreApplication"]