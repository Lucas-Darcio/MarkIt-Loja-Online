FROM node:22-trixie-slim AS build
WORKDIR /usr/app
COPY package*.json .
RUN npm ci
COPY . .
RUN npm run build

FROM node:22-trixie-slim AS production
WORKDIR /usr/app
ENV NODE_ENV=production
COPY package*.json .
RUN npm ci --omit=dev
COPY --from=build /usr/app/dist ./dist
EXPOSE 6666
CMD [ "npm", "run", "start:prod" ]

FROM node:22-trixie-slim AS development
WORKDIR /usr/app
ENV NODE_ENV=development
COPY . .
EXPOSE 6666
CMD [ "npm", "start"]